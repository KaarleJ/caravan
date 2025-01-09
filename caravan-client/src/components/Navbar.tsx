import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu";
import Link from "next/link";
import { ModeToggle } from "./ui/mode-toggle";
import { auth } from "@/auth";
import Image from "next/image";

export default async function Navbar() {
  const session = await auth();
  return (
    <NavigationMenu className="px-48 py-3 max-w-full w-full shadow-md justify-between absolute top-0 right-0 border-b backdrop-opacity-75 backdrop-blur-lg">
      <Link href="/">
        <Image
          src="/caravan-banner.png"
          width={100}
          height={50}
          alt="caravan link"
          className="w-auto"
        />
      </Link>
      <NavigationMenuList>
        <NavigationMenuItem>
          <Link href="/" legacyBehavior passHref>
            <NavigationMenuLink className={navigationMenuTriggerStyle()}>
              Documentation
            </NavigationMenuLink>
          </Link>
        </NavigationMenuItem>
        <ModeToggle />
        {session ? (
          <div>ProfileMenu</div>
        ) : (
          <NavigationMenuItem>
            <Link href="/login" legacyBehavior passHref>
              <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                Login
              </NavigationMenuLink>
            </Link>
          </NavigationMenuItem>
        )}
      </NavigationMenuList>
    </NavigationMenu>
  );
}