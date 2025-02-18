import { z } from "zod";

export const tripFormSchema = z.object({
  name: z.string({
    required_error: "A name is required",
  }),
  description: z.string({
    required_error: "A description is required.",
  }),
  date: z.date({
    required_error: "A date is required.",
  }),
});
