import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
    const [phoneNumber, setPhoneNumber] = useState("");
    const navigate = useNavigate();

  const handleLogin = async (value) => {
    setPhoneNumber(value);
    try {
        const response = await login(value);
        navigate("/otp")
        
    } catch (error) {
        console.error("Failed Otp")
    }

  };
  return (
    <Login
      heading="Login"
      id="phonenumber"
      type="phonenumber"
      placehoder="Enter Mobile No."
      label="Enter Mobile No."
      onSubmit={handleLogin}
    />
  );
};

export default LoginPage;
