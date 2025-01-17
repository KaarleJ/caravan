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
  id: string;
  email: string;
  roles: string[];
}
