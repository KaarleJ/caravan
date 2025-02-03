"use client";
import { Trip } from "@/types";
import { Button } from "./ui/button";

export default function TripSideCard({ trip }: { trip: Trip }) {
  console.log(trip);
  return (
    <div className="border-r py-5 px-20 flex flex-col justify-start gap-10 w-[30rem]">
      <div className="flex flex-col gap-4">
        <Button>Delete Trip</Button>
        <Button>Cancel Trip</Button>
      </div>
    </div>
  );
}
