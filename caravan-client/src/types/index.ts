export type CreateUserRequest = {
  id?: string;
  email: string;
  profilePicture?: string | null;
};

export type createTripRequest = {
  name: string;
  description: string;
  date: Date;
};

export type User = {
  id: string;
  email: string;
  profilePicture?: string | null;
}

export type Ride = {
  id: string;
  driver: User;
  passengers: User[];
  seats: number;
}


export type Trip = {
  id: string;
  name: string;
  description: string;
  date: string;
  createdBy: User;
  participants: User[];
  rides: Ride[];
  status: "upcoming" | "completed" | "canceled";
};