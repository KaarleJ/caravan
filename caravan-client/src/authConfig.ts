import NextAuth from "next-auth";
import Auth0 from "next-auth/providers/auth0";
import type { Provider } from "next-auth/providers";
import { createUser } from "./actions/authActions";
import { CreateUserRequest } from "./types";

const providers: Provider[] = [
  Auth0({
    authorization: {
      params: { audience: process.env.AUTH_AUTH0_AUDIENCE, prompt: "login" },
    },
  }),
];

export const { handlers, auth, signIn, signOut } = NextAuth({
  providers,
  pages: {
    signIn: "/signin",
  },
  callbacks: {
    async signIn({ user }) {
      const payload: CreateUserRequest = {
        id: user.id,
        email: user.email as string,
        profilePicture: user.image as string | null,
      };

      console.log("user id in signIn callback", user.id);

      const res = await createUser(payload);
      return res;
    },
    async jwt({ token, account, user }) {
      if (user) {
        console.log("user id in jwt callback", user.id);
        token.id = user.id;
      }
      if (account?.provider === "auth0") {
        token.apiToken = account.access_token;
      }
      return token;
    },
    async session({ session, token }) {
      console.log("user id in session callback", token.id);
      session.apiToken = token.apiToken as string;
      session.user.id = token.id as string;
      return session;
    },
  },
});
