"use client";
import { Trip } from "@/types";
import { Button } from "./ui/button";
import { useState } from "react";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { tripFormSchema } from "@/lib/formSchemas";
import { z } from "zod";
import { useRouter } from "next/navigation";
import { createTrip } from "@/actions/tripsActions";
import TripForm from "./TripForm";

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
    const res = await createTrip(data);
    if ("error" in res) {
      form.setError("root", { message: res.error });
      return;
    }
    router.push(`/trips/${res.id}`);
  }

  return edit ? (
    <TripForm form={form} onSubmit={onSubmit} />
  )
  : (
    <div className="flex flex-col gap-4">
      <h2 className="text-3xl">{trip.name}</h2>
      <p className="text-gray-600">{trip.description}</p>
      <Button onClick={() => setEdit(true)}>Edit</Button>
    </div>
  );
}
