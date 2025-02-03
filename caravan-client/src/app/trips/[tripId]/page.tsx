import TripSideBar from "@/components/TripSideBar";
import apiClient from "@/lib/apiClient";
import { Trip } from "@/types";

export default async function TripPage({
  params,
}: {
  params: { tripId: string };
}) {
  const { tripId } = await params;
  const { data: trip } = await apiClient.get<Trip>(`/trips/${tripId}`);
  return (
    <div className="pt-20 h-screen flex">
      <TripSideBar trip={trip} />
      <div className="p-4 w-full">
        <h1 className="text-7xl pb-4">Trip {trip.name}</h1>
      </div>
    </div>
  );
}
