"use server";
import { z } from "zod";
import { signIn } from "../authConfig";
import { signInFormSchema } from "../lib/formSchemas";
import { redirect } from "next/navigation";
import { AuthError } from "next-auth";

/*
  This is the function that will be called when the user submits the sign in form
  This basically wraps the signIn function inside a server action, so that it can be called client-side
*/
export async function signInWithCredentials(
  values: z.infer<typeof signInFormSchema>,
  mode: "signin" | "signup"
) {
  const path = mode === "signin" ? "login" : "register";
  try {
    await signIn("credentials", {
      email: values.email,
      password: values.password,
      mode,
    });
  } catch (e) {
    if (e instanceof AuthError) {
      redirect(`/${path}?error=${e.cause?.err}`);
    } else {
      redirect(`/${path}?error=Login failed`);
    }
  }
}
