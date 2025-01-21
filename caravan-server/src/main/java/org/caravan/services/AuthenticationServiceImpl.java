package org.caravan.services;

import org.caravan.dto.AuthRequest;
import org.caravan.dto.GetTokenRequest;
import org.caravan.dto.TokenResponse;
import org.caravan.dto.UserResponse;
import org.caravan.mapper.UserMapper;
import org.caravan.model.User;
import org.caravan.repository.UserRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

  @ConfigProperty(name = "quarkus.api.shared-secret")
  String apiSharedSecret;

  @Inject
  private UserRepository userRepository;

  public UserResponse login(AuthRequest request) {
    User user = userRepository.findByEmail(request.getEmail());

    BCrypt.Result result = BCrypt.verifyer().verify(
        request.getPassword().toCharArray(),
        user.getPassword().toCharArray());

    if (result.verified) {
      return UserMapper.toUserResponse(user);
    } else {
      throw new RuntimeException("Invalid password");
    }
  }

  public UserResponse register(AuthRequest request) {
    String hashedPassword = BCrypt.withDefaults().hashToString(
        12,
        request.getPassword().toCharArray());

    User user = User.builder()
        .email(request.getEmail())
        .password(hashedPassword)
        .build();

    userRepository.persist(user);

    return UserMapper.toUserResponse(user);
  }

  public TokenResponse generateToken(GetTokenRequest request) {
    String clientSecret = request.getClientSecret();
    if (clientSecret == null || !clientSecret.equals(apiSharedSecret)) {
      throw new RuntimeException("Invalid client secret");
    }

    String jwt = Jwt
        .issuer("https://caravan.com/issuer")
        .subject(request.getUserId())
        .upn(request.getEmail())
        .groups(request.getRole())
        .expiresIn(3600)
        .sign();

    return TokenResponse.builder()
        .token(jwt)
        .build();
  }
}
