package org.caravan.services;

import org.caravan.dto.CreateUserRequest;
import org.caravan.dto.UserResponse;

public interface UserService {

  public UserResponse createUser(CreateUserRequest request);
}
