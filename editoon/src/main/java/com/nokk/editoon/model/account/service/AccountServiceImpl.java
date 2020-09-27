package com.nokk.editoon.model.account.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nokk.editoon.domain.Token;
import com.nokk.editoon.exception.ExpiredTokenException;
import com.nokk.editoon.exception.InternalServerException;
import com.nokk.editoon.exception.UnAuthorizationException;
import com.nokk.editoon.exception.UnknownException;
import com.nokk.editoon.model.account.dto.AccountDTO;
import com.nokk.editoon.model.account.dto.AccountModifyDTO;
import com.nokk.editoon.model.account.dto.LoginInfoDTO;
import com.nokk.editoon.model.account.entity.AccountEntity;
import com.nokk.editoon.model.account.repository.AccountRepo;
import com.nokk.editoon.model.account.repository.ProfileImageRepo;
import com.nokk.editoon.model.editoon.repository.EditoonRepo;
import com.nokk.editoon.util.CreateUUID;
import com.nokk.editoon.util.JwtTokenUtil;
import com.nokk.editoon.util.MapperUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Service
public class AccountServiceImpl implements IAccountService {
	private static final String IMAGE_FOLDER = "/resource/image/profileImg";
//	private static final String IMAGE_FOLDER = "C:/image"; // 이런식으로함

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	JwtTokenUtil jtu;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private CreateUUID createUUID;

	@Autowired
	private EditoonRepo editoonRepo;

	@Autowired
	private ProfileImageRepo profileImageRepo;

	@Autowired
	private MapperUtil mapperUtil;

	/*
	 * 반면 사용자가 접속을 뜸하게 하는 경우에도 RefreshToken의 만료 기간의 늘어나기 때문에, 핸드폰이 탈취되는 등의 경우에 지속적인
	 * 이용이 가능 할 수 있습니다. 이를 막는 방법으로 인증이 확실히 요구되는 경우 비밀번호를 한번 더 묻는다거나, 비밀번호 변경 등의 이벤트가
	 * 발생 할 때 강제로 RefshToken을 만료시키는 처리를 해주는 것이 좋습니다.
	 */

	@Override
	public LoginInfoDTO getLoginInfo(String accessToken) {
		String email = "";
		Map<String, Object> map = new HashMap<String, Object>();
		LoginInfoDTO loginInfoDTO = null;
		try {
			email = jwtTokenUtil.getUsernameFromToken(accessToken);
		} catch (MalformedJwtException e) {
			throw new UnAuthorizationException("getLoginInfo \n" + "please Check accessToken : " + accessToken);
		} catch (ExpiredJwtException e) {
			throw new ExpiredTokenException("AccessToken " + accessToken);
		}
		if (email == null || email.equals(""))
			throw new UnAuthorizationException("getLoginInfo \n" + "please Check accessToken : " + accessToken);

		Optional<AccountEntity> optional = accountRepo.findAccountByEmail(email);
		if (optional.isPresent()) {
			AccountEntity accountEntity = optional.get();
			loginInfoDTO = mapperUtil.convertToDTO(accountEntity, LoginInfoDTO.class);
		} else
			throw new UnAuthorizationException("getLoginInfo \n" + "please Check accessToken : " + accessToken);
		return loginInfoDTO;
	}

	@Override
	public boolean validAccountCheck(String email, String password) {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(10);
		Optional<AccountEntity> optional = accountRepo.findAccountByEmail(email);
		AccountEntity account = new AccountEntity();
		boolean check = false;

		if (optional.isPresent()) {
			account = optional.get();
			check = bcryptPasswordEncoder.matches(password, account.getPassword());
			if (check) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canUseFileExtension(String fileExtension) {
		if (!"JPG".equalsIgnoreCase(fileExtension) && !"JPEG".equalsIgnoreCase(fileExtension)
				&& !"PNG".equalsIgnoreCase(fileExtension)) {
			return false;
		}

		return true;
	}

	@Override
	public void saveAccountNameAndImage(AccountModifyDTO accountModifyDTO) {
		try {
			// image 처리 부분
			// CASE 1. default -> 사용자 이미지(기존 image 는 default / client에서 넘겨준 MultiFile이 null
			// 이 아닐경우)
			// -> 사용자 이미지 UUID 를 생성해서 파일에 저장시키고 account image 에 xxxx.jpg 저장시킴
			// CASE 2. 사용자 이미지 -> 사용자 이미지
			// -> 사용자 이미지 값을 받아서 새로운 사용자 이미지 값으로 저장시켜버림.
			// CASE 3. 사용자 이미지 -> default
			// -> 사용자 이미지 값을 받아서 삭제시킨다음 account image를 default로 저장시킴

			String newImageName = "default.jpg";
			if (accountModifyDTO.getImage().equals("default.jpg") && accountModifyDTO.getMultipartFile() != null
					&& !accountModifyDTO.getMultipartFile().isEmpty()) {
				// CASE 1.
				String fileExtension = StringUtils
						.getFilenameExtension(accountModifyDTO.getMultipartFile().getOriginalFilename());

				if (canUseFileExtension(fileExtension)) {
					newImageName = createUUID.createUUID(fileExtension);
				}
				profileImageRepo.saveFile(accountModifyDTO.getMultipartFile(), IMAGE_FOLDER, newImageName);
			} else if (!accountModifyDTO.getImage().equals("default.jpg")) {
				if (accountModifyDTO.getMultipartFile() != null && !accountModifyDTO.getMultipartFile().isEmpty()) {
					// CASE 2.
					newImageName = accountModifyDTO.getImage(); // 기존에 있던 파일 지우고 그 이름으로 다시 설정
					profileImageRepo.deleteFile(IMAGE_FOLDER, newImageName);
					profileImageRepo.saveFile(accountModifyDTO.getMultipartFile(), IMAGE_FOLDER, newImageName);
				} else if (accountModifyDTO.getMultipartFile() == null
						|| accountModifyDTO.getMultipartFile().isEmpty()) {
					// CASE 3.
					String deleteImageName = accountModifyDTO.getImage();
					profileImageRepo.deleteFile(IMAGE_FOLDER, deleteImageName);
				}
			}


			// name 변경 부분
			int ret = -1;
			ret = accountRepo.updateAccountNameAndImage(accountModifyDTO.getEmail(), accountModifyDTO.getName(),
					newImageName);
			if (ret != 1)
				throw new InternalServerException("saveAccountName \n" + "detail Exception Info : maybe check mariaDB");
		} catch (Exception e) {
			throw new InternalServerException("saveAccountName \n" + "detail Exception Info :" + e.getMessage());
		}

	}

	@Override
	public void saveAccountPassword(AccountDTO accountDTO) {
		try {
			int ret = -1;
			BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(10);
			accountDTO.setPassword(bcryptPasswordEncoder.encode(accountDTO.getPassword()));

			ret = accountRepo.updateAccountPassword(accountDTO.getEmail(), accountDTO.getPassword());
			if (ret != 1)
				throw new InternalServerException(
						"saveAccountPassword \n" + "detail Exception Info : maybe check mariaDB");
		} catch (Exception e) {
			throw new InternalServerException("saveAccountPassword \n" + "detail Exception Info :" + e.getMessage());
		}

	}

	@Override
	public void deleteAccount(String email) {
		try {
			int ret = -1;
			int accountNo = -1;

			Optional<AccountEntity> optional = accountRepo.findAccountByEmail(email);
			if (optional.isPresent()) {
				accountNo = optional.get().getNo();
				profileImageRepo.deleteFile(IMAGE_FOLDER, optional.get().getImage());
			} else {
				throw new UnknownException(email + "is not exist. maybe check mariadb.");
			}

			ret = accountRepo.deleteAccount(email); // 여기서 에러 나면 false 출력하게 어떻게?

			ValueOperations<String, Object> vop = redisTemplate.opsForValue();
			Token token = (Token) vop.get(email);
			if (token != null)
				redisTemplate.expire(email, 1, TimeUnit.MILLISECONDS);
			if (ret != 1)
				throw new InternalServerException("deleteAccount \n" + "detail Exception Info : maybe check mariaDB");

			editoonRepo.removeEditoon(accountNo);

		} catch (Exception e) {
			throw new InternalServerException("deleteAccount \n" + "detail Exception Info :" + e.getMessage());
		}
	}

}
