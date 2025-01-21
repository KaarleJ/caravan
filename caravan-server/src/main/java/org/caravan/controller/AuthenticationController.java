package org.caravan.controller;

import org.caravan.dto.AuthRequest;
import org.caravan.dto.GetTokenRequest;
import org.caravan.dto.TokenResponse;
import org.caravan.dto.UserResponse;
import org.caravan.services.AuthenticationService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationController {

  @Inject
  private AuthenticationService authenticationService;

  @POST
  @Transactional
  @Path("/login")
  public UserResponse login(AuthRequest request) {
    return authenticationService.login(request);
  }

  @POST
  @Transactional
  @Path("/register")
  public UserResponse register(AuthRequest request) {
    return authenticationService.register(request);
  }

  @POST
  @Transactional
  @Path("/token")
  @PermitAll
  public TokenResponse generateToken(GetTokenRequest request) {
    return authenticationService.generateToken(request);
  }
}