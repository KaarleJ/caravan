"use server";

import apiClient from "@/lib/apiClient";
import { createTripRequest, Trip } from "@/types";
import axios from "axios";

export async function createTrip(trip: createTripRequest): Promise<Trip | { error: string }> {
  try {
    const { data } = await apiClient.post<Trip>("/trips", trip);
    return data;
  } catch (error) {
    if (axios.isAxiosError(error)) {
      return { error: error.response?.data.message.split(":")[1] || "An error occurred" };
    }
    return { error: "An error occurred" };
  }
}
