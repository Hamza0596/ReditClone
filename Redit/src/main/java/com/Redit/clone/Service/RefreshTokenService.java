package com.Redit.clone.Service;

import com.Redit.clone.Model.RefreshToken;

public interface RefreshTokenService {
public RefreshToken generateRefreshToken();

void validateRefreshToken(String refreshToken);

void deleteRefreshToken(String refreshToken);
}
