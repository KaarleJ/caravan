"use client";
import { useRouter, useSearchParams } from "next/navigation";
import { Checkbox } from "./ui/checkbox";
import { Label } from "./ui/label";
import { DateRangePicker } from "./DateRangePicker";
import { DateRange } from "react-day-picker";
import { format, parseISO } from "date-fns";
import { Button } from "./ui/button";

export default function TripsSideBar() {
  const router = useRouter();
  const searchParams = useSearchParams();

  function handleStatusFilter(value: string, checked: boolean) {
    const params = new URLSearchParams(searchParams.toString());
    const currentStatuses = params.getAll("status");

    if (checked) {
      params.append("status", value);
    } else {
      const newStatuses = currentStatuses.filter((status) => status !== value);
      params.delete("status");
      newStatuses.forEach((status) => params.append("status", status));
    }

    router.push(`/trips?${params.toString()}`);
  }

  const initialDateRange = {
    from: searchParams.get("startDate")
      ? parseISO(searchParams.get("startDate")!)
      : undefined,
    to: searchParams.get("endDate")
      ? parseISO(searchParams.get("endDate")!)
      : undefined,
  };

  const handleDateChange = (range: DateRange | undefined) => {
    const params = new URLSearchParams(searchParams.toString());

    params.delete("startDate");
    params.delete("endDate");

    if (range?.from) {
      params.set("startDate", format(range.from, "yyyy-MM-dd"));
    }
    if (range?.to) {
      params.set("endDate", format(range.to, "yyyy-MM-dd"));
    }

    router.push(`/trips?${params.toString()}`);
  };

  return (
    <div className="border-r py-20 px-20 flex flex-col justify-between gap-10 w-[30rem]">
      <div>
        <h3 className="text-3xl font-bold pb-2">Filter trips</h3>
        {["upcoming", "completed", "canceled"].map((status) => (
          <div
            key={status}
            className="flex items-center justify-between px-2 py-2"
          >
            <Label
              htmlFor={`status-${status}`}
              className="text-base capitalize"
            >
              {status}
            </Label>
            <Checkbox
              id={`status-${status}`}
              checked={searchParams.getAll("status").includes(status)}
              onCheckedChange={(checked) =>
                handleStatusFilter(status, !!checked)
              }
            />
          </div>
        ))}
      </div>
      <div>
        <h3 className="text-3xl font-bold pb-2">Date range</h3>
        <DateRangePicker
          className="py-4"
          value={initialDateRange}
          onChange={handleDateChange}
        />
      </div>
      <div className="grow flex flex-col justify-end">
        <Button className="w-full text-lg">New trip</Button>
      </div>
    </div>
  );
}
