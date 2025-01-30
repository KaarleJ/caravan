package org.caravan.mapper;

import java.util.List;
import java.util.stream.Collectors;

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

  public static List<UserResponse> toUserResponseList(List<User> users) {
    return users.stream()
        .map(UserMapper::toUserResponse)
        .collect(Collectors.toList());
  }
}
