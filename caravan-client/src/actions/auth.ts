"use server";

import { signOut as authSignOut } from "@/authConfig";

export async function signOut() {
  await authSignOut();
}