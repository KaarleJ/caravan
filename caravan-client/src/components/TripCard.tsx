import { Trip } from "@/types";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import Link from "next/link";
import { format } from "date-fns";
import TripStatus from "./TripStatus";

export default function TripCard({ trip }: { trip: Trip }) {
  return (
    <Link passHref href={`/trips/${trip.id}`}>
      <Card className="hover:shadow-lg hover:bg-primary/5 transition-all duration-150">
        <CardHeader className="flex flex-row justify-between">
          <CardTitle>{trip.name}</CardTitle>
          <CardDescription>
            <p className="flex gap-4 items-center">
              {format(trip.date, "d.MM.yyyy")}{" "}
              <TripStatus status={trip.status} />
            </p>
          </CardDescription>
        </CardHeader>
        <CardContent>{trip.description}</CardContent>
        <CardFooter className="flex gap-8">
          <div className="flex items-center space-x-1">
            <span role="img" aria-label="rides">
              🚗
            </span>
            <span>{trip.rides?.length || 0} Rides</span>
          </div>
          <div className="flex items-center space-x-1">
            <span role="img" aria-label="participants">
              🙋
            </span>
            <span>{trip.participants?.length || 0} Participants</span>
          </div>
        </CardFooter>
      </Card>
    </Link>
  );
}
