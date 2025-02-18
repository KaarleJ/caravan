import Link from "next/link";

export default function NotFound() {
  return (
    <div className="w-full h-screen flex flex-col items-center justify-center space-y-4 pb-20">
      <h2 className="text-3xl font-medium">404: Not Found</h2>
      <p className="text-lg">The page you&apos;re looking for doesn&apos;t exist</p>
      <Link href="/" className="text-lg underline text-blue-700 hover:brightness-50">Return Home</Link>
    </div>
  );
}
