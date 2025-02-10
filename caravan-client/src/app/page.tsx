import { signIn } from "@/authConfig";
import { Button } from "@/components/ui/button";
import Image from "next/image";

export default async function Home() {
  return (
    <main className="flex flex-col lg:flex-row gap-4 py-24 md:py-52 px-4 md:px-48">
      <div>
        <h1 className="text-primary text-8xl italic mb-6">Caravan</h1>
        <div className="ml-2 space-y-6">
          <p className="text-2xl">Share rides easily with your friends</p>
          <p className="text-lg">
            Caravan makes ride-sharing with friends a breeze. Plan your trip,
            share the link, and let drivers and passengers sign up—no fuss, no
            spreadsheets. It’s a one-stop solution to see who’s driving, who’s
            riding, and how many seats are left.
          </p>
        </div>
        <form
          action={async () => {
            "use server";
            await signIn("auth0");
          }}
        >
          <Button type="submit" className="my-16 p-6 text-xl">
            Get Started
          </Button>
        </form>
      </div>
      <div className="rounded-sm shadow-lg shadow-primary overflow-hidden">
        <Image
          src="/caravan.jpg"
          width={1600}
          height={1000}
          alt="caravan"
          className="w-full h-full object-cover"
        />
      </div>
    </main>
  );
}
