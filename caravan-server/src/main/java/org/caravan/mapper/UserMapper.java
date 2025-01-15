package org.caravan.mapper;

import org.caravan.dto.UserResponse;
import org.caravan.model.User;

public class UserMapper {
  public static UserResponse toUserResponse(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .profilePicture(user.getProfilePicture())
        .build();
  }
}
