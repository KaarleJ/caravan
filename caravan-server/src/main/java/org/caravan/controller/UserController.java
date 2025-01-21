package org.caravan.controller;

import org.caravan.dto.CreateUserRequest;
import org.caravan.dto.UserResponse;
import org.caravan.services.UserService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.security.PermitAll;
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

  @ConfigProperty(name = "quarkus.api.shared-secret")
  String apiSharedSecret;

  @POST
  @Transactional
  @PermitAll
  public UserResponse createUser(CreateUserRequest request) {
    if (!request.getClientSecret().equals(apiSharedSecret)) {
      throw new IllegalArgumentException("Invalid client secret");
    }
    return userService.createUser(request);
  }
}
