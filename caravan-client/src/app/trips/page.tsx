import { auth } from "@/authConfig";
import apiClient from "@/lib/apiClient";

export default async function Trips({
  searchParams,
}: {
  searchParams: Promise<{ [key: string]: string | string[] | undefined }>;
}) {
  const session = await auth();
  const user = session?.user;
  const params = await searchParams;

  const res = await apiClient.get("/trips", {
    params,
  });
  const queries = res.data;
  return (
    <div className="p-4">
      <h1 className="text-7xl pb-4">Trips for {user?.email}</h1>
      <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
        veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
        commodo consequat. Duis aute irure dolor in reprehenderit in voluptate
        velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint
        occaecat cupidatat non proident, sunt in culpa qui officia deserunt
        mollit anim id est laborum.
      </p>
      <h3 className="text-lg font-semibold">Status</h3>
      {[queries.status].flat().map((status, i) => (
        <div key={i}>{status}</div>
      ))}
      <h3 className="text-lg font-semibold">Daterange</h3>
      <p>
        {queries.startDate} - {queries.endDate}
      </p>
    </div>
  );
}
