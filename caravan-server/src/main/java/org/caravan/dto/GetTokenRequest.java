package org.caravan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTokenRequest {
  private String clientSecret;
  private String userId;
  private String email;
  private String role;
}
