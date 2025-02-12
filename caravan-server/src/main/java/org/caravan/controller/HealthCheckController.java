package org.caravan.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/health")
public class HealthCheckController {
  
  @GET
  public String healthCheck() {
    return "OK";
  }
}
