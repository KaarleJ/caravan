package org.caravan.security;

import org.caravan.model.User;
import org.caravan.repository.UserRepository;

import io.quarkus.security.ForbiddenException;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserContextImpl implements UserContext {

  @Inject
  SecurityIdentity securityIdentity;

  @Inject
  UserRepository userRepository;

  public User getCurrentUser() {
    String currentUserId = securityIdentity.getPrincipal().getName();
    System.out.println("currentUserId: " + currentUserId);
    return userRepository.findById(currentUserId);
  }

  public void validateOwnerShip(String resourceOwnerId) {
    String currentUserId = securityIdentity.getPrincipal().getName();
    if (!currentUserId.equals(resourceOwnerId)) {
      throw new ForbiddenException("You do not own this resource.");
    }
  }

}
