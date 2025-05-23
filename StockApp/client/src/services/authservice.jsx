import axiosInstance from "../api/axiosInterceptor";
import { AUTH_BASE_URL } from "../api/api"


export const  oAuthLogin = async (code) => {
  return await axiosInstance.post(`${AUTH_BASE_URL}/auth-service/oauth/google`, {code});
}

export const login = async (phoneNumber) => {
  return await axiosInstance.post(`$${AUTH_BASE_URL}/auth-service/login`, {phoneNumber});
}

export const sendOtp = async (otp) => {
  return await axiosInstance.post(`$${AUTH_BASE_URL}/auth-service/login`, {otp});
}