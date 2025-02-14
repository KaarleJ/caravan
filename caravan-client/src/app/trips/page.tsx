import { auth } from "@/authConfig";
import TripCard from "@/components/TripCard";
import TripsSideBar from "@/components/TripsSideBar";
import { apiClient } from "@/lib/apiClient";
import { Trip } from "@/types";

export default async function Trips({
  searchParams,
}: {
  searchParams: Promise<{ [key: string]: string | string[] | undefined }>;
}) {
  const session = await auth();
  const user = session?.user;
  const params = await searchParams;

  const trips = await apiClient<Trip[]>("/trips", {
    params,
  });

  return (
    <>
      <TripsSideBar />
      <div className="p-4 w-full">
        <h1 className="text-7xl pb-4">Trips for {user?.email}</h1>
        <div className="grid grid-cols-3 gap-4 w-full">
          {trips.map((trip) => (
            <TripCard key={trip.id} trip={trip} />
          ))}
        </div>
      </div>
    </>
  );
}
