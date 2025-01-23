import { auth } from "@/authConfig";
import Image from "next/image";

export default async function Venues() {
  const session = await auth();
  const user = session?.user;
  return (
    <div>
      <p>Logged in as {user?.name}</p>
      <p>Email: {user?.email}</p>
      <Image
        src={user?.image as string}
        alt="Profile picture"
        width={200}
        height={200}
      />
    </div>
  );
}
