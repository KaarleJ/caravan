package org.caravan.services;

import org.caravan.dto.AuthRequest;
import org.caravan.dto.GetTokenRequest;
import org.caravan.dto.TokenResponse;
import org.caravan.dto.UserResponse;

public interface AuthenticationService {

  public UserResponse login(AuthRequest request);

  public UserResponse register(AuthRequest request);

  public TokenResponse generateToken(GetTokenRequest request);

  public void validateClientSecret(String clientSecret);
}
