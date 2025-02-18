import BackButton from "./BackButton";

export default function SideBar({ children }: { children?: React.ReactNode }) {
  return (
    <div className="px-20 py-20 border-r w-[35rem] relative">
      <BackButton className="absolute top-5 left-10" />
      <div className="flex flex-col justify-between gap-5 h-full">{children}</div>
    </div>
  );
}
