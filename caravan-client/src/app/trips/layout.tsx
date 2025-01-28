import TripsSideBar from "@/components/TripsSideBar";


export default function VenuesLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="py-20 flex">
      <TripsSideBar />
      {children}
    </div>
  );
}
