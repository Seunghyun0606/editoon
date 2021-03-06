package com.nokk.editoon.model.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.domain.Persistable;
import org.springframework.security.web.csrf.CsrfFilter;

import com.nokk.editoon.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "account")
public class AccountEntity implements Persistable<String> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, name = "account_no")
	private int no;

	@Column(nullable = false, name = "account_email")
	private String email;

	@Column(nullable = false, name = "account_name")
	private String name;

	@Column(nullable = false, name = "account_password")
	private String password;

	@Column(nullable = false, name = "account_role")
	private Role role;

	@Column(name = "account_image")
	private String image;
	
	public String getRoleKey() {
		return this.role.getKey();
	}

	@Override
	public String getId() {
		return this.no + "";
	}

	@Override
	public boolean isNew() {
		return false;
	}

}
