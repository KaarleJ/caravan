import { auth } from "@/auth";
import axios, { InternalAxiosRequestConfig, AxiosError } from "axios";

const apiClient = axios.create({
  baseURL: process.env.API_URL || "http://localhost:8080",
});

apiClient.interceptors.request.use(
  async (config: InternalAxiosRequestConfig) => {
    const session = await auth();

    if (session?.accessToken) {
      config.headers.set("Authorization", `Bearer ${session.accessToken}`);
    }

    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

export default apiClient;
