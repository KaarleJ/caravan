package org.caravan.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
  @NotBlank(message = "clientSecret is required")
  private String clientSecret;

  @NotBlank(message = "id is required")
  private String id;

  @NotBlank(message = "email is required")
  @Email(message = "invalid email")
  private String email;

  private String profilePicture;
}
