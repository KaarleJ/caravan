import SideBar from "@/components/SideBar";

export default function TripsLoading() {
  return (
    <div className="flex justify-between w-full">
      <SideBar />
      <div className="flex items-center justify-center w-full grow">
        <div className="animate-spin rounded-full h-32 w-32 border-b-2 border-accent-foreground"></div>
      </div>
    </div>
  );
}
