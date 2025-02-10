import { clsx, type ClassValue } from "clsx";
import { twMerge } from "tailwind-merge";

const apiUrl = process.env.API_URL || "http://localhost:8080";

function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export { cn, apiUrl };
