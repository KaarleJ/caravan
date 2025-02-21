package org.caravan.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response.Status;
import org.caravan.exception.UserNotFoundException;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

public class GlobalExceptionMapperTest {

  @Test
  public void testHandleUserNotFound() {
    GlobalExceptionMapper mapper = new GlobalExceptionMapper();
    UserNotFoundException exception = new UserNotFoundException("Dummy error");
    RestResponse<String> response = mapper.handleUserNotFound(exception);

    assertEquals(Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    assertEquals("{\"message\": \"User not found\"}", response.getEntity());
  }

  @Test
  public void testHandleNotFound() {
    GlobalExceptionMapper mapper = new GlobalExceptionMapper();
    NotFoundException exception = new NotFoundException("Dummy error");
    RestResponse<String> response = mapper.handleNotFound(exception);

    assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    assertEquals("{\"message\": \"Resource not found\"}", response.getEntity());
  }
}