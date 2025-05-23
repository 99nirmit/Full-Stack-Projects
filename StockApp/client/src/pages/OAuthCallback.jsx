import React, { useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import {oAuthLogin } from "../services/authservice";

const OAuthCallback = () => {
  const [searchParams] = useSearchParams();
  const code = searchParams.get("code");
  console.log(code + " code");
  

  useEffect(() => {
    if (code) {
      exchangeCodeForToken(code);
    }
  }, [code]);

  const exchangeCodeForToken = async (code) => {
    try {
      const response = await oAuthLogin(code);
      console.log(response);
      const data = response.data;
      console.log(data);

      //to dashboard navigate 
      

    } catch (error) {
      console.error("Failed to authenticate: ", error);
    }
  };

  return <div>OAuthCallback</div>;
};

export default OAuthCallback;
