package org.caravan.controller;

import org.caravan.dto.AuthRequest;
import org.caravan.dto.GetTokenRequest;
import org.caravan.dto.TokenResponse;
import org.caravan.dto.UserResponse;
import org.caravan.security.AuthenticationService;
import org.caravan.security.TokenService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationController {

  @Inject
  private AuthenticationService authenticationService;

  @Inject
  private TokenService tokenService;

  @POST
  @Transactional
  @Path("/login")
  public UserResponse login(@Valid AuthRequest request) {
    return authenticationService.login(request);
  }

  @POST
  @Transactional
  @Path("/register")
  public UserResponse register(@Valid AuthRequest request) {
    return authenticationService.register(request);
  }

  @POST
  @Transactional
  @Path("/token")
  @PermitAll
  public TokenResponse generateToken(@Valid GetTokenRequest request) {
    tokenService.validateClientSecret(request.getClientSecret());
    return tokenService.generateToken(request);
  }
}