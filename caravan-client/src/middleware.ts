import { auth } from "@/authConfig";
import { NextResponse } from "next/server";

export default auth((req) => {
  if (["/login", "/register"].includes(req.nextUrl.pathname) && req.auth) {
    return NextResponse.redirect(new URL("/trips", req.url));
  }
  if (req.nextUrl.pathname === "/trips" && !req.auth) {
    return NextResponse.redirect(new URL("/login", req.url));
  }
  return NextResponse.next();
});
