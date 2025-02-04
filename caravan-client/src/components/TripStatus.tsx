import { CalendarDays, CircleCheckBig, CircleX  } from "lucide-react";

export default function TripStatus({ status }: { status: "upcoming" | "completed" | "canceled" }) {
  switch (status) {
    case "upcoming":
      return (
        <CalendarDays size={24} className="text-secondary-foreground/70" />
      );
    case "completed":
      return (
        <CircleCheckBig size={24} className="text-green-500" />
      );
    case "canceled":
      return (
        <CircleX size={24} className="text-destructive" />
      );
    default:
      return null;
  }
}