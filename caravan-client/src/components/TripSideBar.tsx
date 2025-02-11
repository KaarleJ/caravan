import { Trip } from "@/types";
import { Button } from "./ui/button";
import { Session } from "next-auth";
import BackButton from "./BackButton";
import EditTripDetailsForm from "./EditTripDetailsForm";
import { deleteTrip } from "@/actions/tripsActions";
import { redirect } from "next/navigation";

export default function TripSideCard({
  trip,
  session,
}: {
  trip: Trip;
  session: Session | null;
}) {
  const owner = trip.createdBy.id === session?.user?.id;

  async function destroy() {
    "use server";
    await deleteTrip(trip.id);
    redirect("/trips");
  }

  return owner ? (
    <div className="flex flex-col px-20 py-20 justify-between border-r gap-5 w-[35rem] relative">
      <BackButton  className="absolute top-5 left-10"/>
      <div className="grow">
        <EditTripDetailsForm trip={trip} />
      </div>

      <div className="flex flex-col gap-4 py-10">
        <Button>Complete Trip</Button>
        <Button
          variant="outline"
          className="text-destructive border-destructive"
        >
          Cancel Trip
        </Button>
      </div>
      <form action={destroy} className="flex flex-col gap-4">
        <Button type="submit" variant="destructive">Delete Trip</Button>
      </form>
    </div>
  ) : (
    <div className="flex flex-col px-20 py-20 justify-between border-r w-[35rem]">
      <BackButton />
      <Button variant="destructive">Leave trip</Button>
    </div>
  );
}
