package org.caravan.security;

import org.caravan.dto.GetTokenRequest;
import org.caravan.dto.TokenResponse;

public interface TokenService {
  public TokenResponse generateToken(GetTokenRequest request);

  public void validateClientSecret(String clientSecret);
}
