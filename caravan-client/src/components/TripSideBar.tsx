"use client";
import { Trip } from "@/types";
import { Button } from "./ui/button";
import { Session } from "next-auth";

export default function TripSideCard({
  trip,
  session,
}: {
  trip: Trip;
  session: Session | null;
}) {
  return (
    <div className="border-r py-5 px-20 flex flex-col justify-start gap-10 w-[30rem]">
      {session?.user?.id === trip.createdBy.id ? (
        <div className="flex flex-col gap-4">
          <Button variant="destructive">Delete Trip</Button>
          <Button variant="destructive">Cancel Trip</Button>
        </div>
      ) : (
        <Button>Leave trip</Button>
      )}
    </div>
  );
}
