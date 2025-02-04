export type AuthRequest = {
  email: string;
  password: string;
};

export type CreateUserRequest = {
  id?: string;
  email: string;
  profilePicture?: string | null;
};

export type GetTokenRequest = {
  userId: string;
  email: string;
  role: string;
}

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