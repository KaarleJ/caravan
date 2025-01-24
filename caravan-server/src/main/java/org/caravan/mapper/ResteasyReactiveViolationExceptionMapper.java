package org.caravan.mapper;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.stream.Collectors;
import java.util.Map;

@Provider
public class ResteasyReactiveViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

  /*
   * When a ConstraintViolationException is thrown, this method is called to
   * generate a Response object that will be sent back to the client.
   */
  @Override
  public Response toResponse(ConstraintViolationException exception) {
    String combinedMessages = exception.getConstraintViolations().stream()
    .map(v -> {
      String field = v.getPropertyPath().toString();
      String message = v.getMessage();
      String splitField = field.substring(field.lastIndexOf(".") + 1);
      return splitField + ": " + message;
    })
    .collect(Collectors.joining(", "));

return Response.status(Response.Status.BAD_REQUEST)
    .entity(Map.of("message", combinedMessages))
    .build();
  }
}