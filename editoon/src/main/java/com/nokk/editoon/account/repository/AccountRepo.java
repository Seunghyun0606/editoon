package com.nokk.editoon.account.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nokk.editoon.account.domain.entity.AccountEntity;

public interface AccountRepo extends JpaRepository<AccountEntity, Integer> {
	Optional<AccountEntity> findAccountByEmail(String email);

}
