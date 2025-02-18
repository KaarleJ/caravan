import { signOut } from "@/actions/auth";
import { NextResponse } from "next/server";

/*
  * Sign out the user and redirect to the homepage.
  * Used for programmatic sign out.
*/
export async function GET() {
  await signOut();
  return NextResponse.redirect("/");
}
