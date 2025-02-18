import { signIn } from "@/authConfig";
import { Button } from "@/components/ui/button";

export default async function Home() {
  return (
    <main>
      <div className="relative h-screen bg-[url('/caravan.jpg')] bg-center bg-cover z-10">
        <div className="absolute inset-0 z-0 bg-black opacity-25"></div>
        <div className="relative z-10 flex flex-col items-center justify-center pb-20 h-screen">
          <div className="px-4 max-w-2xl text-center text-white">
            <p className="text-lg">Share rides easily</p>
            <div className="mt-5 max-w-2xl">
              <h1 className="text-6xl font-extrabold">Caravan</h1>
            </div>
            <div className="mt-5 max-w-3xl">
              <p className="text-xl">
                Plan your trip, share the link, and let drivers and passengers sign up—no fuss, no spreadsheets.
              </p>
            </div>
            <form
              className="mt-8"
              action={async () => {
                "use server";
                await signIn("auth0");
              }}
            >
              <Button type="submit">Get Started</Button>
            </form>
          </div>
        </div>
      </div>
    </main>
  );
}