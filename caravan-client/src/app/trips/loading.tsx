import SideBar from "@/components/SideBar";
import { Skeleton } from "@/components/ui/skeleton";

export default function TripsLoading() {
  return (
    <div className="flex justify-between w-full">
      <SideBar>
        <div className="space-y-4">
          <Skeleton className="w-full h-12 rounded-3xl" />
          <Skeleton className="w-full h-12 rounded-3xl" />
        </div>
        <div className="space-y-4">
          <Skeleton className="w-full h-12 rounded-3xl" />
          <Skeleton className="w-full h-12 rounded-3xl" />
        </div>
      </SideBar>
      <div className="pt-24 grid grid-cols-3 grid-rows-4 p-4 gap-x-4 w-full">
        <Skeleton className="w-full h-[9rem] rounded-3xl" />
        <Skeleton className="w-full h-[9rem] rounded-3xl" />
        <Skeleton className="w-full h-[9rem] rounded-3xl" />
        <Skeleton className="w-full h-[9rem] rounded-3xl" />
        <Skeleton className="w-full h-[9rem] rounded-3xl" />
        <Skeleton className="w-full h-[9rem] rounded-3xl" />
      </div>
    </div>
  );
}
