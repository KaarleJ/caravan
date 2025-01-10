import SignUpForm from "@/components/SignUpForm";

export default async function Register() {
  return (
    <div className="flex items-center justify-center w-full h-screen bg-[url('/caravan.jpg')] bg-center bg-cover">
      <div className="border rounded-lg shadow-md p-8 bg-background w-[22rem]">
        <h2 className="text-3xl">Sign up</h2>
        <p className="text-gray-500 text-xl">Create an account</p>
        <SignUpForm />
      </div>
    </div>
  );
}