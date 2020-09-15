package com.nokk.editoon.account.service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nokk.editoon.account.domain.dto.AccountSignUpDTO;
import com.nokk.editoon.account.domain.entity.AccountEntity;
import com.nokk.editoon.account.repository.AccountRepo;
import com.nokk.editoon.domain.Role;
import com.nokk.editoon.domain.Token;
import com.nokk.editoon.exception.InternalServerException;
import com.nokk.editoon.util.JwtTokenUtil;

import io.lettuce.core.RedisConnectionException;

@Service
public class NonMemberSerivceImpl implements INonMemberService{

	@Autowired
	private AccountRepo accountRepo;

	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	JwtTokenUtil jtu;

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	public void signUp(AccountSignUpDTO accountSignUpDTO) {
		BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder(10);
		accountSignUpDTO.setPassword(bcryptPasswordEncoder.encode(accountSignUpDTO.getPassword()));

		accountRepo.save(AccountEntity.builder()
									.email(accountSignUpDTO.getEmail())
									.name(accountSignUpDTO.getName())
									.password(accountSignUpDTO.getPassword())
									.role(Role.USER)
									.build()
						);
	}

	@Override
	public boolean emailCheck(String email) {
		Optional<AccountEntity> optional = accountRepo.findAccountByEmail(email);
		if (optional.isPresent()) {
			return false;
		} else {
			return true; // 해당부분에 대해서는 예외처리로
		}
	}

	@Override
	public void emailAuthSend(String email) {
		Random random = new Random();
		int randNum = random.nextInt(3829375) + 293817;
		String setfrom = "Editoon";
		String tomail = email;
		String title = "[Editoon] 회원가입 인증 이메일 입니다.";
		String content = System.getProperty("line.separator") + System.getProperty("line.separator") + " 인증 코드입니다 : "
				+ randNum;

		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			mimeMessageHelper.setFrom(setfrom);
			mimeMessageHelper.setTo(tomail);
			mimeMessageHelper.setSubject(title);
			mimeMessageHelper.setText(content);
			mailSender.send(mimeMessage);

			/*
			 * redis save part
			 */

			String key = email + "-auth";
			String value = jwtTokenUtil.generateEmailAuthToken(randNum + "");

			Token token = new Token();
			token.setEmail(email);
			token.setToken(value);
			ValueOperations<String, Object> vop = redisTemplate.opsForValue();
			vop.set(key, token);
			redisTemplate.expire(key, 60 * 5, TimeUnit.SECONDS); // 5분
		} catch (Exception e) {
			throw new InternalServerException("emailAuthSend Internal Server Exception \n" + "detail Exception Info :" + e.getMessage());
		}
	}

	@Override
	public boolean emailAuthCheck(String email, String authNum) {
		/*
		 * redis 에서 가져와서 같은 값인지 검증하는 부분
		 */
		try {
			ValueOperations<String, Object> vop = redisTemplate.opsForValue();
			String key = email + "-auth";
			
			Token token = (Token) vop.get(key);
			if (token != null) {
				String value = jwtTokenUtil.getEmailAuthNumFromToken(token.getToken());
				if (authNum.equals(value)) {
					return true;
				}
			}
			
		} catch(Exception e) {
			throw new InternalServerException("emailAuthCheck Internal Server Exception \n" + "detail Exception Info :" + e.getMessage());
		}
		
		return false;
	}

}
