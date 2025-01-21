import "next-auth";
import "next-auth/jwt"

declare module "next-auth" {
  interface Session {
    apiToken?: string;
  }
}

declare module "next-auth/jwt" {
  interface JWT {
    id?: string;
    email?: string;
    role?: string;
    apiToken?: string;
    apiTokenExpiry?: number;
  }
}