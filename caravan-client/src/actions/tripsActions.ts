"use server";

import apiClient from "@/lib/apiClient";
import { createTripRequest, Trip } from "@/types";

export async function createTrip(trip: createTripRequest) {
  const res = await apiClient.post<Trip>("/trips", {
    ...trip,
  });

  return res.data;
}