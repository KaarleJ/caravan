"use server";
import apiClient from "@/lib/apiClient";
import { AuthRequest, CreateUserRequest } from "@/types";

const apiUrl = process.env.API_URL || "http://localhost:4000";

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
    await apiClient.post("/users", user);
    return true;
  } catch (e) {
    console.error(e);
    return false;
  }
}
