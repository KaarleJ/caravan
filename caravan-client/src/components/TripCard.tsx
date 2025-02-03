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

export default function TripCard({ trip }: { trip: Trip }) {
  return (
    <Link passHref href={`/trips/${trip.id}`}>
      <Card>
        <CardHeader>
          <CardTitle>{trip.name}</CardTitle>
        </CardHeader>
        <CardContent>
          <CardDescription>{trip.description}</CardDescription>
        </CardContent>
        <CardFooter>
          <p>Start: {trip.date}</p>
        </CardFooter>
      </Card>
    </Link>
  );
}
