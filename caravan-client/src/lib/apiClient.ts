import { auth } from "@/authConfig";
import axios, { InternalAxiosRequestConfig, AxiosError } from "axios";
import { apiUrl } from "./utils";
import qs from "qs";

const apiClient = axios.create({
  baseURL: apiUrl,
  paramsSerializer: (params) => {
    return qs.stringify(params, { arrayFormat: "repeat" });
  },
});

apiClient.interceptors.request.use(
  async (config: InternalAxiosRequestConfig) => {
    const session = await auth();

    if (session?.apiToken) {
      config.headers.set("Authorization", `Bearer ${session.apiToken}`);
    }

    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  }
);

export default apiClient;
