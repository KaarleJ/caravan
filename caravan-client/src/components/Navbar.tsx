import {
  NavigationMenu,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu";
import Link from "next/link";
import { ModeToggle } from "./ui/mode-toggle";

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
        <ModeToggle/>
      </NavigationMenuList>
    </NavigationMenu>
  );
}
