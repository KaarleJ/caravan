package org.caravan.security;

import org.caravan.dto.GetTokenRequest;
import org.caravan.dto.TokenResponse;
import org.caravan.exception.InvalidClientSecretException;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TokenServiceImpl implements TokenService {

  @ConfigProperty(name = "api.shared-secret")
  String apiSharedSecret;

  public TokenResponse generateToken(GetTokenRequest request) {
    String clientSecret = request.getClientSecret();
    validateClientSecret(clientSecret);

    String jwt = Jwt
        .issuer("https://caravan.com/issuer")
        .subject(request.getUserId())
        .upn(request.getEmail())
        .groups(request.getRole())
        .expiresIn(3600)
        .sign();

    return TokenResponse.builder()
        .token(jwt)
        .expiresIn(3600)
        .build();
  }

  public void validateClientSecret(String clientSecret) {
    if (clientSecret == null || !clientSecret.equals(apiSharedSecret)) {
      throw new InvalidClientSecretException("Invalid client secret");
    }
  }
}
