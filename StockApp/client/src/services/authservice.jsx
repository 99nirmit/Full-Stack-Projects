import axiosInstance from "../api/axiosInterceptor";
import { AUTH_BASE_URL } from "../api/api"


export const  oAuthLogin = async (code) => {
  return await axiosInstance.post(`${AUTH_BASE_URL}/oauth/google`, {code});
}