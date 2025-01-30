package org.caravan.controller;

import org.caravan.dto.CreateUserRequest;
import org.caravan.dto.UserResponse;
import org.caravan.security.TokenService;
import org.caravan.services.UserService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

  @Inject
  private UserService userService;

  @Inject
  private TokenService tokenService;

  @POST
  @Transactional
  @PermitAll
  public UserResponse createUser(@Valid CreateUserRequest request) {
    tokenService.validateClientSecret(request.getClientSecret());
    return userService.createUser(request);
  }
}
