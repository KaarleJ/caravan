"use server";
import apiClient from "@/lib/apiClient";
import { CreateUserRequest } from "@/types";

export async function createUser(user: CreateUserRequest) {
  try {
    await apiClient.post("/users", { ...user });
    return true;
  } catch (e) {
    console.error(e);
    return false;
  }
}
