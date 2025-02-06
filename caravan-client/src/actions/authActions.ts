"use server";
import apiClient, { serverClient } from "@/lib/apiClient";
import { sharedSecret } from "@/lib/utils";
import { AuthRequest, CreateUserRequest, GetTokenRequest } from "@/types";

/*
  Credentials provider authorize function
*/
export async function login(user: AuthRequest) {
  const res = await apiClient.post("/auth/login", {
    ...user,
    clientSecret: sharedSecret,
  });
  return res.data;
}

/*
  Credentials provider authorize function for sign up
*/
export async function register(user: AuthRequest) {
  const res = await apiClient.post("/auth/register", {
    ...user,
  });
  return res.data;
}

export async function createUser(user: CreateUserRequest) {
  try {
    await apiClient.post("/users", { ...user, clientSecret: sharedSecret });
    return true;
  } catch (e) {
    console.error(e);
    return false;
  }
}

/*
  Fetch api token to interact with a custom backend
  Uses serverClient to avoid infinite loop, since the apiClient triggers the jwt callback
*/
export async function getApiToken(user: GetTokenRequest) {
  const res = await serverClient.post("/auth/token", {
    ...user,
    clientSecret: sharedSecret,
  });
  return res.data;
}
