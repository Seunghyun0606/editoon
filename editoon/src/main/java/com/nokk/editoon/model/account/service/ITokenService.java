package com.nokk.editoon.model.account.service;

public interface ITokenService {
	public void newAccessTokenByAccessToken(String accessToken);

	public void newAccessTokenByRefreshToken(String refreshToken);
}
