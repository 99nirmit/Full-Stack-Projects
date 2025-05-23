import React, { useState } from "react";
import Login from "./Login";
import { useNavigate } from "react-router-dom";

const OtpVerification = () => {
  const [otp, setOtp] = useState("");
  const navigate = useNavigate();

  const submitOtp = async (value) => {
    setOtp(value);
    try {
      const response = await sendOtp(value);
      navigate("/dashboard");
    } catch (error) {
      console.error("Wrong Otp");
    }

  };
  return (
    <Login
      heading="Otp Verification"
      id="otp"
      type="otp"
      placehoder="Enter Otp"
      label="Enter Otp"
      onSubmit={submitOtp}
    />
  );
};

export default OtpVerification;
