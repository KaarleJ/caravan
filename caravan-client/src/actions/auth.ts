"use server";
import apiClient from "@/lib/apiClient";
import { AuthRequest, CreateUserRequest, GetTokenRequest } from "@/types";

const apiUrl = process.env.API_URL || "http://localhost:4000";
const apiSecret = process.env.API_SHARED_SECRET;

/*
  Credentials provider authorize function
*/
export async function login(user: AuthRequest) {
  const res = await fetch(`${apiUrl}/auth/login`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });
  if (!res.ok) {
    throw new Error("Failed to fetch user");
  }
  return res.json();
}

/*
  Credentials provider authorize function for sign up
*/
export async function register(user: AuthRequest) {
  const res = await fetch(`${apiUrl}/auth/register`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(user),
  });

  if (!res.ok) {
    throw new Error("Failed to create user");
  }

  return res.json();
}

export async function createUser(user: CreateUserRequest) {
  try {
    await apiClient.post("/users", { ...user, clientSecret: apiSecret });
    return true;
  } catch (e) {
    console.error(e);
    return false;
  }
}

/*
  Fetch api token to interact with a custom backend
*/
export async function getApiToken(user: GetTokenRequest) {
  try {
    const res = await apiClient.post("/auth/token", {
      ...user,
      clientSecret: apiSecret,
    });
    return res.data;
  } catch (e) {
    console.error(e);
    throw new Error("Failed to fetch token");
  }
}
