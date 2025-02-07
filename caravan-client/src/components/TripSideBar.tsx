import { Trip } from "@/types";
import { Button } from "./ui/button";
import { Session } from "next-auth";
import BackButton from "./BackButton";
import EditTripDetailsForm from "./EditTripDetailsForm";

export default function TripSideCard({
  trip,
  session,
}: {
  trip: Trip;
  session: Session | null;
}) {
  const owner = trip.createdBy.id === session?.user?.id;

  return owner ? (
    <div className="flex flex-col px-20 py-20 justify-between border-r gap-20 w-[35rem]">
      <BackButton />
      <div className="grow">
        <EditTripDetailsForm trip={trip} />
      </div>

      <div className="flex flex-col gap-4">
        <Button>Complete Trip</Button>
        <Button
          variant="outline"
          className="text-destructive border-destructive"
        >
          Cancel Trip
        </Button>
      </div>
      <div className="flex flex-col gap-4">
        <Button variant="destructive">Delete Trip</Button>
      </div>
    </div>
  ) : (
    <div className="flex flex-col px-20 py-20 justify-between border-r w-[35rem]">
      <BackButton />
      <Button variant="destructive">Leave trip</Button>
    </div>
  );
}
