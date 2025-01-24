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
public class GetTokenRequest {
  @NotBlank(message = "clientSecret is required")
  private String clientSecret;

  @NotBlank(message = "userId is required")
  private String userId;

  @NotBlank(message = "email is required")
  @Email(message = "invalid email")
  private String email;

  @NotBlank(message = "role is required")
  private String role;
}
