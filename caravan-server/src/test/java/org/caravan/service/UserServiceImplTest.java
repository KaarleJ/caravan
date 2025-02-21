package org.caravan.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.caravan.dto.CreateUserRequest;
import org.caravan.dto.UserResponse;
import org.caravan.model.User;
import org.caravan.repository.UserRepository;
import org.caravan.services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.component.QuarkusComponentTest;
import jakarta.inject.Inject;

@QuarkusComponentTest
public class UserServiceImplTest {

  @Inject
  private UserServiceImpl userService;

  @InjectMock
  private UserRepository userRepository;

  @Test
  public void testCreateUser() {
    CreateUserRequest request = CreateUserRequest.builder()
        .id("1")
        .email("test@test.com")
        .profilePicture("test")
        .build();

    Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(null);

    UserResponse response = userService.createUser(request);

    assertEquals(request.getId(), response.getId());
    assertEquals(request.getEmail(), response.getEmail());
    assertEquals(request.getProfilePicture(), response.getProfilePicture());
  }

  @Test
  public void testCreateUserWhenUserExists() {
    CreateUserRequest request = CreateUserRequest.builder()
        .id("1")
        .email("test@test.com")
        .profilePicture("test")
        .build();

    Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(new User());

    UserResponse response = userService.createUser(request);
    assertEquals(null, response);
  }
}
