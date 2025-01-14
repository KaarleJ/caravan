package org.caravan.services;

import org.caravan.dto.AuthRequest;
import org.caravan.dto.UserResponse;

public interface AuthenticationService {

  public UserResponse login(AuthRequest request);

  public UserResponse register(AuthRequest request);
}
