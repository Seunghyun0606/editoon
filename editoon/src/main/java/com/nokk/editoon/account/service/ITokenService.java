package com.nokk.editoon.account.service;

public interface ITokenService {
	public void newAccessTokenByAccessToken(String accessToken);

	public void newAccessTokenByRefreshToken(String refreshToken);
}
