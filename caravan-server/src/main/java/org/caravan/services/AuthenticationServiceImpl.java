package org.caravan.services;

import org.caravan.dto.AuthRequest;
import org.caravan.dto.UserResponse;
import org.caravan.mapper.UserMapper;
import org.caravan.model.User;
import org.caravan.repository.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuthenticationServiceImpl implements AuthenticationService {

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
}
