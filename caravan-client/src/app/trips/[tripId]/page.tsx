import { auth } from "@/authConfig";
import TripSideBar from "@/components/TripSideBar";
import { apiClient } from "@/lib/apiClient";
import { Trip } from "@/types";

export default async function TripPage({
  params,
}: {
  params: Promise<{ tripId: string }>;
}) {
  const { tripId } = await params;
  const session = await auth();

  const trip = await apiClient<Trip>(`/trips/${tripId}`);
  return (
    <>
      <TripSideBar trip={trip} session={session} />
      <div className="p-4 w-full">
        <h1 className="text-7xl pb-4">{trip.name}</h1>
        <p className="text-lg px-2 text-accent-foreground/75">
          {trip.description}
        </p>
      </div>
    </>
  );
}
