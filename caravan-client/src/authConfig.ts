import NextAuth from "next-auth";
import Auth0 from "next-auth/providers/auth0";
import type { Provider } from "next-auth/providers";
import { createUser } from "./actions/userActions";
import { CreateUserRequest } from "./types";

const issuer = process.env.AUTH_AUTH0_ISSUER;
const audience = process.env.AUTH_AUTH0_AUDIENCE;
const clientId = process.env.AUTH_AUTH0_ID;
const clientSecret = process.env.AUTH_AUTH0_SECRET;

const providers: Provider[] = [
  Auth0({
    authorization: {
      params: {
        audience,
        prompt: "login",
        scope: "openid profile email offline_access",
        access_type: "offline",
      },
    },
  }),
];

export const { handlers, auth, signIn, signOut } = NextAuth({
  providers,
  callbacks: {
    /*
     * This callback is called when a user signs in.
     * It calls the api to create a user if it doesn't exist.
     */
    async signIn({ user, account }) {
      user.id = account?.providerAccountId;

      const payload: CreateUserRequest = {
        id: user.id,
        email: user.email as string,
        profilePicture: user.image as string | null,
      };

      const res = await createUser(payload);
      return res;
    },
    /*
     * This callback is called whenever a JSON Web Token is created.
     * It should return the JWT that will be returned to the client.
     */
    async jwt({ token, account }) {
      // First login, save details to the token
      if (account) {
        return {
          ...token,
          id: account.providerAccountId,
          apiToken: account.access_token,
          expiresAt: account.expires_in,
          refreshToken: account.refresh_token,
        };
      } else if (token.expiresAt && Date.now() < token.expiresAt * 1000) {
        // Subsequent logins with valid expiration, return previous token
        return token;
      } else {
        // Subsequent logins, but the token has expired, try to refresh it
        if (!token.refreshToken) throw new TypeError("Missing refresh_token");
        try {
          const response = await fetch(`${issuer}/oauth/token`, {
            method: "POST",
            body: new URLSearchParams({
              client_id: clientId!,
              client_secret: clientSecret!,
              grant_type: "refresh_token",
              refresh_token: token.refreshToken!,
            }),
          });

          const tokensOrError = await response.json();

          if (!response.ok) throw tokensOrError;

          const newTokens = tokensOrError as {
            access_token: string;
            expires_in: number;
            refresh_token?: string;
          };

          return {
            ...token,
            accessToken: newTokens.access_token,
            expiresAt: Math.floor(Date.now() / 1000 + newTokens.expires_in),
            refreshToken: newTokens.refresh_token
              ? newTokens.refresh_token
              : token.refreshToken,
          };
        } catch (error) {
          console.error("Error refreshing access_token", error);
          // If we fail to refresh the token, return an error so we can handle it on the navbar
          token.error = "RefreshTokenError";
          return token;
        }
      }
    },
    /*
     * This callback is called when a session is checked.
     * It should return the current session object after updating it
     * based on the current token and user.
     */
    async session({ session, token }) {
      session.apiToken = token.apiToken as string;
      session.user.id = token.id as string;
      return session;
    },
  },
});
