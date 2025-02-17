import { auth } from "@/authConfig";
import { apiUrl } from "./utils";
import qs from "qs";

/**
 * Builds a full URL using the base URL and optional query params.
 * Query params are serialized with arrayFormat=repeat.
 */
function buildUrl(path: string, queryParams?: Record<string, unknown>): string {
  const url = new URL(path, apiUrl);

  if (queryParams) {
    const queryString = qs.stringify(queryParams, { arrayFormat: "repeat" });
    url.search = queryString;
  }
  return url.toString();
}

/**
 * A lightweight fetch wrapper that:
 *  - Appends Authorization: Bearer <token> if `session.apiToken` is present
 *  - Supports query params
 *  - Serializes a JSON body if provided
 *  - Throws an error if the response is not ok
 */
export async function apiClient<T = unknown>(
  path: string,
  options?: {
    method?: "GET" | "POST" | "PUT" | "PATCH" | "DELETE";
    params?: Record<string, unknown>;
    body?: unknown;
  }
): Promise<T> {
  const { method = "GET", params, body } = options || {};
  const session = await auth();

  const url = buildUrl(path, params);

  const headers: Record<string, string> = {};
  if (session?.apiToken) {
    headers["Authorization"] = `Bearer ${session.apiToken}`;
  }

  let fetchBody: string | undefined;
  if (body !== undefined && body !== null) {
    headers["Content-Type"] = "application/json";
    fetchBody = JSON.stringify(body);
  }

  const response = await fetch(url, {
    method,
    headers,
    body: fetchBody,
  });

  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(`Error ${response.status}${errorText && ": " + errorText}`);
  }

  if (response.body) {
    return response.json() as Promise<T>;
  } else {
    return null as T;
  }
}
