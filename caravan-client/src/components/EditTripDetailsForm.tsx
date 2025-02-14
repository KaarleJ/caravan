"use client";
import { Trip } from "@/types";
import { Button } from "./ui/button";
import { useState } from "react";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { tripFormSchema } from "@/lib/formSchemas";
import { z } from "zod";
import { useRouter } from "next/navigation";
import { updateTrip } from "@/actions/tripsActions";
import TripForm from "./TripForm";
import { format } from "date-fns";

export default function EditTripDetailsForm({ trip }: { trip: Trip }) {
  const [edit, setEdit] = useState(false);
  const router = useRouter();

  const form = useForm<z.infer<typeof tripFormSchema>>({
    resolver: zodResolver(tripFormSchema),
    defaultValues: {
      name: trip.name,
      description: trip.description,
      date: new Date(trip.date),
    },
  });

  async function onSubmit(data: z.infer<typeof tripFormSchema>) {
    const res = await updateTrip(trip.id, data);
    if (typeof res === "object" && res !== null && "error" in res) {
      form.setError("root", { message: res.error });
      return;
    }
    setEdit(false);
    router.push(`/trips/${trip.id}`);
  }

  return edit ? (
    <TripForm form={form} onSubmit={onSubmit} />
  )
  : (
    <div className="flex flex-col gap-2">
      <h2 className="text-3xl font-bold">{trip.name}</h2>
      <p className="text-accent-foreground/75">{format(trip.date, "dd.MM.yyyy")}</p>
      <p className="text-accent-foreground/90 text-lg">{trip.description}</p>
      <Button className="my-2" onClick={() => setEdit(true)}>Edit</Button>
    </div>
  );
}
