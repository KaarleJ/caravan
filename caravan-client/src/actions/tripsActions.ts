"use server";

import { apiClient } from "@/lib/apiClient";
import { createTripRequest, Trip, updateTripRequest } from "@/types";

export async function createTrip(
  trip: createTripRequest
): Promise<Trip | { error: string }> {
  try {
    const data = await apiClient<Trip>("/trips", { method: "POST", body: trip });
    return data;
  } catch (error: Error | unknown) {
    if (error instanceof Error) {
      return {
        error: error.message.split(":")[1] || "An error occurred",
      };
    }
    return { error: "An error occurred" };
  }
}

export async function updateTrip(
  tripId: string,
  trip: updateTripRequest
): Promise<Trip | { error: string }> {
  try {
    const data = await apiClient<Trip>(`/trips/${tripId}`, { method: "PUT", body: trip });
    return data;
  } catch (error: Error | unknown) {
    if (error instanceof Error) {
      return {
        error: error.message.split(":")[1] || "An error occurred",
      };
    }
    return { error: "An error occurred" };
  }
}

export async function deleteTrip(tripId: string): Promise<void> {
  await apiClient(`/trips/${tripId}`, { method: "DELETE" });
}
