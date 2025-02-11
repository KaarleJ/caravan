"use server";
import { apiClient} from "@/lib/apiClient";
import { CreateUserRequest } from "@/types";

export async function createUser(user: CreateUserRequest) {
  try {
    await apiClient("/users", { method: "POST", body: user });
    return true;
  } catch (e) {
    console.error(e);
    return false;
  }
}
