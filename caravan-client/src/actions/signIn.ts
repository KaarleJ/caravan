"use server";
import { z } from "zod";
import { signIn } from "../authConfig";
import { signInFormSchema } from "../lib/formSchemas";
import { redirect } from "next/navigation";

/*
  This is the function that will be called when the user submits the sign in form
  This basically wraps the signIn function inside a serer action, so that it can be called client-side
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
    console.error(e);
    if (e instanceof Error) {
      console.log(`/${path}?error=${e.message}`);
      redirect(`/${path}?error=${e.message}`);
    } else {
      console.log(`/${path}?error=Login failed`);
      redirect(`/${path}?error=Login failed`);
    }
  }
}
