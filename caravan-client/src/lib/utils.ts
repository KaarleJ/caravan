import { clsx, type ClassValue } from "clsx";
import { twMerge } from "tailwind-merge";

export const apiUrl = process.env.API_URL || "http://localhost:4000";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}