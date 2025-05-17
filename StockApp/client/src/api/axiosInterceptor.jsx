import axios from "axios"
import { AUTH_BASE_URL } from "./api"

const axiosInstance = axios.create({
    baseURL: AUTH_BASE_URL,
})

axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");
        if(token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
    (response) => {
        if(response.data?.token) {
            localStorage.setItem("token", response.data.token);
        }
        return response;
    },
    (error) => Promise.reject(error)

);

export default axiosInstance;