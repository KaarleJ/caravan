import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu";
import Link from "next/link";
import { ModeToggle } from "./ui/mode-toggle";
import { SignedIn, SignedOut, SignInButton, UserButton } from "@clerk/nextjs";

export default function Navbar() {
  return (
    <NavigationMenu className="px-8 py-3 max-w-full bg-primary justify-between">
      <div className="text-primary-foreground text-lg">
        Application logo here
      </div>
      <NavigationMenuList>
        <NavigationMenuItem>
          <Link href="/" legacyBehavior passHref>
            <NavigationMenuLink className={navigationMenuTriggerStyle()}>
              Documentation
            </NavigationMenuLink>
          </Link>
        </NavigationMenuItem>
        <ModeToggle />
        <div className="p-2 flex items-center justify-center text-primary-foreground hover:brightness-75 transition-all">
          <SignedOut>
            <SignInButton />
          </SignedOut>
          <SignedIn>
            <UserButton appearance={{
              elements: {
                userButtonAvatarBox: 'h-9 w-9'
              }
            }} />
          </SignedIn>
        </div>
      </NavigationMenuList>
    </NavigationMenu>
  );
}
