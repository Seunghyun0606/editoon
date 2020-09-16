package com.nokk.editoon.account.service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nokk.editoon.account.domain.dto.AccountDTO;
import com.nokk.editoon.account.domain.dto.PrimitiveAccountDTO;
import com.nokk.editoon.account.domain.entity.AccountEntity;
import com.nokk.editoon.account.repository.AccountRepo;
import com.nokk.editoon.domain.Token;
import com.nokk.editoon.exception.InternalServerException;
import com.nokk.editoon.util.JwtTokenUtil;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	JwtTokenUtil jtu;

	/*
	 * 반면 사용자가 접속을 뜸하게 하는 경우에도 RefreshToken의 만료 기간의 늘어나기 때문에, 핸드폰이 탈취되는 등의 경우에 지속적인
	 * 이용이 가능 할 수 있습니다. 이를 막는 방법으로 인증이 확실히 요구되는 경우 비밀번호를 한번 더 묻는다거나, 비밀번호 변경 등의 이벤트가
	 * 발생 할 때 강제로 RefreshToken을 만료시키는 처리를 해주는 것이 좋습니다.
	 */

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
	public void saveAccountName(PrimitiveAccountDTO primitiveAccountDTO) {
		try {
			int ret = -1;
			ret = accountRepo.updateAccountName(primitiveAccountDTO.getEmail(), primitiveAccountDTO.getName());
			System.out.println(ret);
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
			System.out.println(ret);
			if (ret != 1)
				throw new InternalServerException("saveAccountPassword \n" + "detail Exception Info : maybe check mariaDB" );
		} catch (Exception e) {
			throw new InternalServerException("saveAccountPassword \n" + "detail Exception Info :" + e.getMessage());
		}

	}

	@Override
	public void deleteAccount(String email) {
		try {
			int ret = -1;
			ret = accountRepo.deleteAccount(email); // 여기서 에러 나면 false 출력하게 어떻게?
			
			ValueOperations<String, Object> vop = redisTemplate.opsForValue();
			Token token = (Token) vop.get(email);
			if (token != null)
				redisTemplate.expire(email, 1, TimeUnit.MILLISECONDS);
			if(ret != 1)
				throw new InternalServerException("deleteAccount \n" + "detail Exception Info : maybe check mariaDB" );
		} catch (Exception e) {
			throw new InternalServerException("deleteAccount \n" + "detail Exception Info :" + e.getMessage());
		}
	}

}
