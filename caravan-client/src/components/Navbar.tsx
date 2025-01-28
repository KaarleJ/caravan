import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu";
import Link from "next/link";
import { ModeToggle } from "./ui/mode-toggle";
import { auth } from "@/authConfig";
import Image from "next/image";
import AvatarMenu from "./AvatarMenu";

export default async function Navbar() {
  const session = await auth();
  return (
    <NavigationMenu className="px-4 md:px-48 py-3 max-w-full w-full shadow-md justify-between fixed top-0 right-0 border-b backdrop-opacity-75 backdrop-blur-lg">
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
            <Link href="/login" legacyBehavior passHref>
              <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                Login
              </NavigationMenuLink>
            </Link>
          </NavigationMenuItem>
        )}
        <ModeToggle />
      </NavigationMenuList>
    </NavigationMenu>
  );
}
