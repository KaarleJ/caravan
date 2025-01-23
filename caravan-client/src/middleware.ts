import { auth } from "@/authConfig";
import { NextResponse } from "next/server";

export default auth((req) => {
  if (["/login", "/register"].includes(req.nextUrl.pathname) && req.auth) {
    return NextResponse.redirect(new URL("/venues", req.url));
  }
  if (req.nextUrl.pathname === "/venues" && !req.auth) {
    return NextResponse.redirect(new URL("/login", req.url));
  }
});
