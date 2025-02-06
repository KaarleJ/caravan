import NextAuth from "next-auth";
import GitHub from "next-auth/providers/github";
import Google from "next-auth/providers/google";
import Credentials from "next-auth/providers/credentials";
import type { Provider } from "next-auth/providers";
import {
  createUser,
  getApiToken,
  login,
  register,
} from "./actions/authActions";
import { CreateUserRequest } from "./types";
import axios from "axios";

const providers: Provider[] = [
  Credentials({
    credentials: {
      email: { label: "Email", type: "email" },
      password: { label: "Password", type: "password" },
      mode: { label: "Mode", type: "text" },
    },
    async authorize(c) {
      const { email, password, mode } = c as {
        email: string;
        password: string;
        mode: "signup" | "signin";
      };

      try {
        if (mode === "signup") {
          return await register({ email, password });
        } else {
          return await login({ email, password });
        }
      } catch (error) {
        if (axios.isAxiosError(error) && error.response) {
          throw new Error(error.response.data);
        }
        throw new Error("Unexpected error during sign-in");
      }
    },
  }),
  GitHub,
  Google,
];

export const providerMap = providers
  .map((provider) => {
    if (typeof provider === "function") {
      const providerData = provider();
      return { id: providerData.id, name: providerData.name };
    } else {
      return { id: provider.id, name: provider.name };
    }
  })
  .filter((provider) => provider.id !== "credentials");

export const { handlers, auth, signIn, signOut } = NextAuth({
  providers,
  pages: {
    signIn: "/signin",
  },
  callbacks: {
    async signIn({ user, account }) {
      if (account?.provider === "credentials") {
        return true;
      }

      const payload: CreateUserRequest = {
        id: user.id,
        email: user.email as string,
        profilePicture: user.image as string | null,
      };

      return await createUser(payload);
    },
    async jwt({ token, user }) {
      // On initial sign-in store user details to token and fetch API token
      if (user) {
        token.id = user.id;
        token.email = user.email as string;
        token.role = "user";

        const { token: apiToken, expiresIn } = await getApiToken({
          userId: user.id as string,
          email: user.email as string,
          role: "user",
        });

        token.apiToken = apiToken;
        token.apiTokenExpiry = Date.now() + expiresIn * 1000;
      }

      // If token is nearly expired, refresh it
      const now = Date.now();
      const shouldRefreshTime = 60 * 1000;
      if (
        token.apiTokenExpiry &&
        now + shouldRefreshTime >= token.apiTokenExpiry
      ) {
        try {
          const { token: newApiToken, expiresIn } = await getApiToken({
            userId: token.id as string,
            email: token.email as string,
            role: token.role as string,
          });
          token.apiToken = newApiToken;
          token.apiTokenExpiry = Date.now() + expiresIn * 1000;
        } catch (error) {
          console.error("Failed to refresh token:", error);
          await signOut();
        }
      }
      return token;
    },
    async session({ session, token }) {
      session.apiToken = token.apiToken as string;
      session.user.id = token.id as string;
      return session;
    },
  },
});
