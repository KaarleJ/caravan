package org.caravan.mapper;

import jakarta.ws.rs.core.Response;

import org.caravan.exception.InvalidClientSecretException;
import org.caravan.exception.InvalidCredentialsException;
import org.caravan.exception.UserNotFoundException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class GlobalExceptionMapper {

  /*
   * Returns HTTP 401 for an InvalidClientSecretException
   */
  @ServerExceptionMapper
  public RestResponse<String> handleUserNotFound(UserNotFoundException e) {
    return RestResponse.status(Response.Status.NOT_FOUND, e.getMessage());
  }
  /*
   * Returns HTTP 401 for an InvalidCredentialsException
   */
  @ServerExceptionMapper
  public RestResponse<String> handleInvalidPassword(InvalidCredentialsException e) {
    return RestResponse.status(Response.Status.UNAUTHORIZED, e.getMessage());
  }

  /*
   * Returns HTTP 401 for an InvalidClientSecretException
   */
  @ServerExceptionMapper
  public RestResponse<String> handleInvalidClientSecret(InvalidClientSecretException e) {
    return RestResponse.status(Response.Status.UNAUTHORIZED, e.getMessage());
  }
}