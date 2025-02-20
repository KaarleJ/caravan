package org.caravan.repository;

import org.caravan.model.User;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@Priority(1)
@Alternative
@ApplicationScoped
public class TestUserRepository extends UserRepository {

  @PostConstruct
  public void init() {
    User user = User.builder().id("test|1234").email("testuser").build();
    persist(user);
  }

}
