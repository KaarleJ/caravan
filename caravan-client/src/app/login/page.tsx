import { Button } from "@/components/ui/button";
import { FaGoogle as Google } from "react-icons/fa";
import { FaGithub as Github } from "react-icons/fa";
import { Separator } from "@/components/ui/separator";
import { signIn, providerMap } from "@/auth";

export default async function Login({
  searchParams,
}: {
  searchParams: Promise<{ [key: string]: string | undefined }>;
}) {
  const callbackUrl = (await searchParams).callbackUrl;
  return (
    <div className="flex items-center justify-center w-full h-screen bg-[url('/caravan.jpg')] bg-center bg-cover">
      <div className="border rounded-lg shadow-md p-8 bg-background">
        <h2 className="text-3xl">Sign in</h2>
        <p className="text-gray-500 text-xl">Sign in to access your account</p>
        <div className="flex flex-col gap-4 my-6">
          {Object.values(providerMap).map((provider) => (
            <form
              key={provider.id}
              action={async () => {
                "use server";
                await signIn(provider.id, {
                  redirectTo: callbackUrl ?? "",
                });
              }}
            >
              <Button type="submit" className="w-full">
                Sign in with {provider.name}{" "}
                <ProviderIcon provider={provider.name} />
              </Button>
            </form>
          ))}
        </div>
        <div className="flex items-center">
          <Separator className="w-[8rem]" />
          <p className="mx-2">or</p>
          <Separator className="w-[8rem]" />
        </div>
        <Button className="w-full my-6">Create an account</Button>
        <p className="text-xl text-center">Already have an account?</p>
        <Button className="w-full my-6">Login</Button>
      </div>
    </div>
  );
}

function ProviderIcon({ provider }: { provider: string }) {
  console.log(provider);
  switch (provider) {
    case "google":
      return <Google size={26} />;
    case "GitHub":
      return <Github size={36} />;
    default:
      return <></>;
  }
}
