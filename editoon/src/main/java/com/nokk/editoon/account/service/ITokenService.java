package com.nokk.editoon.account.service;

public interface ITokenService {
	public boolean newAccessTokenByAccessToken(String accessToken);

	public boolean newAccessTokenByRefreshToken(String refreshToken);

	public boolean newRefreshTokenByRefreshToken(String refreshToken);
}
