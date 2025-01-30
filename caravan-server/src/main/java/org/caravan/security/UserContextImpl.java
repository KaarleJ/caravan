package org.caravan.security;

import org.caravan.model.User;
import org.caravan.repository.UserRepository;
import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.security.ForbiddenException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserContextImpl implements UserContext {

  @Inject
   JsonWebToken jwt;

  @Inject
  UserRepository userRepository;

  public User getCurrentUser() {
    String currentUserId = jwt.getSubject();
    return userRepository.findById(currentUserId);
  }

  public void validateOwnerShip(String resourceOwnerId) {
    String currentUserId = jwt.getSubject();
    if (!currentUserId.equals(resourceOwnerId)) {
      throw new ForbiddenException("You do not own this resource.");
    }
  }

}
