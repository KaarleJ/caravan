package org.caravan.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.caravan.dto.UserResponse;
import org.caravan.model.User;
import org.junit.jupiter.api.Test;

public class UserMapperTest {

  @Test
  public void testToUserResponse() {
    User user = User.builder()
        .id("user1")
        .email("user1@example.com")
        .profilePicture("https://example.com/profile.jpg")
        .build();

    UserResponse response = UserMapper.toUserResponse(user);

    assertNotNull(response);
    assertEquals("user1", response.getId());
    assertEquals("user1@example.com", response.getEmail());
    assertEquals("https://example.com/profile.jpg", response.getProfilePicture());
  }

  @Test
  public void testToUserResponseList() {
    User user1 = User.builder()
        .id("user1")
        .email("user1@example.com")
        .profilePicture("pic1")
        .build();
    User user2 = User.builder()
        .id("user2")
        .email("user2@example.com")
        .profilePicture("pic2")
        .build();

    List<User> users = List.of(user1, user2);
    List<UserResponse> responses = UserMapper.toUserResponseList(users);

    assertNotNull(responses);
    assertEquals(2, responses.size());

    UserResponse r1 = responses.get(0);
    UserResponse r2 = responses.get(1);

    assertEquals("user1", r1.getId());
    assertEquals("user1@example.com", r1.getEmail());
    assertEquals("pic1", r1.getProfilePicture());

    assertEquals("user2", r2.getId());
    assertEquals("user2@example.com", r2.getEmail());
    assertEquals("pic2", r2.getProfilePicture());
  }
}