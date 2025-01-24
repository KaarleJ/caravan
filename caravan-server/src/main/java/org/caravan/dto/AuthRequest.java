package org.caravan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
  @NotBlank(message = "email is required")
  @Email(message = "invalid email")
  private String email;

  @NotBlank(message = "password is required")
  private String password;
}
