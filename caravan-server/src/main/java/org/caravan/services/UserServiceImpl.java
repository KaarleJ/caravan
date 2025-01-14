package org.caravan.services;

import org.caravan.dto.CreateUserRequest;
import org.caravan.dto.UserResponse;
import org.caravan.model.User;
import org.caravan.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserServiceImpl implements UserService {

  @Inject
  private UserRepository userRepository;

  public UserResponse createUser(CreateUserRequest request) {
    User user = User.builder()
        .id(request.getId())
        .email(request.getEmail())
        .profilePicture(request.getProfilePicture())
        .build();

    userRepository.persist(user);

    return UserResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .profilePicture(user.getProfilePicture())
        .build();
  }
}
