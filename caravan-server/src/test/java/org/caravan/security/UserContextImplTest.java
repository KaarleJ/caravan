package org.caravan.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.security.Principal;

import org.caravan.model.User;
import org.caravan.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.security.ForbiddenException;
import io.quarkus.security.identity.SecurityIdentity;

public class UserContextImplTest {

  @Mock
  SecurityIdentity securityIdentity;

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserContextImpl userContext;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetCurrentUser() {
    Principal mockPrincipal = mock(Principal.class);
    when(mockPrincipal.getName()).thenReturn("testUser");
    when(securityIdentity.getPrincipal()).thenReturn(mockPrincipal);

    User expectedUser = User.builder().id("testUser").email("test@example.com").build();
    when(userRepository.findById("testUser")).thenReturn(expectedUser);

    User actualUser = userContext.getCurrentUser();

    assertEquals(expectedUser, actualUser);
  }

  @Test
  public void testValidateOwnershipSuccess() {
    Principal mockPrincipal = mock(Principal.class);
    when(mockPrincipal.getName()).thenReturn("testUser");
    when(securityIdentity.getPrincipal()).thenReturn(mockPrincipal);

    assertDoesNotThrow(() -> userContext.validateOwnerShip("testUser"));
  }

  @Test
  public void testValidateOwnershipFailure() {
    Principal mockPrincipal = mock(Principal.class);
    when(mockPrincipal.getName()).thenReturn("testUser");
    when(securityIdentity.getPrincipal()).thenReturn(mockPrincipal);

    ForbiddenException exception = assertThrows(ForbiddenException.class,
        () -> userContext.validateOwnerShip("anotherUser"));
    assertEquals("You do not own this resource.", exception.getMessage());
  }
}