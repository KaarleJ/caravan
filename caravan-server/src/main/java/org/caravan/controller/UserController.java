package org.caravan.controller;

import org.caravan.dto.CreateUserRequest;
import org.caravan.dto.UserResponse;
import org.caravan.services.UserService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

  @Inject
  private UserService userService;

  @POST
  @Transactional
  public UserResponse createUser(CreateUserRequest request) {
    return userService.createUser(request);
  }
}
