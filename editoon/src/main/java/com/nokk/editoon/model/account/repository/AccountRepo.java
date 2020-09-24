package com.nokk.editoon.model.account.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nokk.editoon.model.account.entity.AccountEntity;

public interface AccountRepo extends JpaRepository<AccountEntity, Integer> {
	Optional<AccountEntity> findAccountByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "update account set account_name = :account_name, account_image = :account_image where account_email = :account_email", nativeQuery = true)
	int updateAccountNameAndImage(String account_email, String account_name, String account_image);
	
	@Transactional
	@Modifying
	@Query(value = "update account set account_password = :account_password where account_email = :account_email", nativeQuery = true)
	int updateAccountPassword(String account_email, String account_password);

	@Transactional
	@Modifying
	@Query(value = "delete from account where account_email = :account_email", nativeQuery = true)
	int deleteAccount(String account_email);
}