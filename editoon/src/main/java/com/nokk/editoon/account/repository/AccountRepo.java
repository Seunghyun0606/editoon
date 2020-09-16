package com.nokk.editoon.account.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.nokk.editoon.account.domain.entity.AccountEntity;

public interface AccountRepo extends JpaRepository<AccountEntity, Integer> {
	Optional<AccountEntity> findAccountByEmail(String email);

	@Transactional
	@Modifying
	@Query(value = "update account set account_name = :account_name, account_password = :account_password where account_email = :account_email", nativeQuery = true)
	int updateAccount(String account_email, String account_name, String account_password);

	@Transactional
	@Modifying
	@Query(value = "delete from account where account_no = :no", nativeQuery = true)
	int deleteAccount(int no);
}