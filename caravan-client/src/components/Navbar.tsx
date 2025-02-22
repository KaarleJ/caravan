import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu";
import Link from "next/link";
import { ModeToggle } from "./ui/mode-toggle";
import { auth, signIn } from "@/authConfig";
import Image from "next/image";
import AvatarMenu from "./AvatarMenu";
import { Button } from "./ui/button";
import { redirect } from "next/navigation";

export default async function Navbar() {
  const session = await auth();

  if (session?.error === "RefreshTokenError") {
    redirect("/api/auth/signout");
  }

  return (
    <NavigationMenu className="px-4 md:px-20 py-3 max-w-full w-full justify-between fixed top-0 right-0 border-b backdrop-opacity-75 backdrop-blur-lg z-50">
      <Link href="/">
        <Image
          src="/caravan-banner.png"
          width={100}
          height={50}
          alt="caravan link"
          className="w-auto"
        />
      </Link>
      <NavigationMenuList className="gap-2">
        <NavigationMenuItem>
          <Link href="/trips" legacyBehavior passHref>
            <NavigationMenuLink className={navigationMenuTriggerStyle()}>
              Trips
            </NavigationMenuLink>
          </Link>
        </NavigationMenuItem>
        {session ? (
          <AvatarMenu user={session.user} />
        ) : (
          <NavigationMenuItem>
            <form
              action={async () => {
                "use server";
                await signIn("auth0", { options: { prompt: "login" } });
              }}
            >
              <Button
                type="submit"
                className={navigationMenuTriggerStyle()}
                variant="ghost"
              >
                Login
              </Button>
            </form>
          </NavigationMenuItem>
        )}
        <ModeToggle />
      </NavigationMenuList>
    </NavigationMenu>
  );
}
