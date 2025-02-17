import "next-auth";
import "next-auth/jwt"

declare module "next-auth" {
  interface Session {
    apiToken?: string;
    email?: string;
    error?: "RefreshTokenError";
  }
}

declare module "next-auth/jwt" {
  interface JWT {
    id?: string;
    accessToken?: string;
    refreshToken?: string;
    expiresAt?: number;
    error?: "RefreshTokenError";
  }
}