package org.caravan.mapper;

import jakarta.ws.rs.core.Response;
import org.caravan.exception.UserNotFoundException;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

public class GlobalExceptionMapper {

  /*
   * Returns HTTP 401 for a UserNotFoundException
   */
  @ServerExceptionMapper
  public RestResponse<String> handleUserNotFound(UserNotFoundException e) {
    return RestResponse.status(Response.Status.NOT_FOUND, "{\"message\": \"User not found\"}");
  }

  /*
   * Override 404 response to include a message
   */
  @ServerExceptionMapper
  public RestResponse<String> handleNotFound(Exception e) {
    return RestResponse.status(Response.Status.NOT_FOUND, "{\"message\": \"Resource not found\"}");
  }
}