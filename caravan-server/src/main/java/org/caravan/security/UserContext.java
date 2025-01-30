package org.caravan.security;

import org.caravan.model.User;

public interface UserContext {
  public User getCurrentUser();

  public void validateOwnerShip(String resourceOwnerId);
}
