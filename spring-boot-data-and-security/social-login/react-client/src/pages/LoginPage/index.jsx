import axios from "axios";
import React, { useState } from "react";
import styled from "styled-components";
import kakaoImage from "../../assets/kakao_login_large_wide.png";

const LoginPage = () => {
  const [userData, setUserData] = useState();

  const REST_API_KEY = import.meta.env.VITE_APP_REST_API_KEY;
  console.log(REST_API_KEY);
  const REDIRECT_URI = "http://localhost:5173/oauth";
  const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

  const handleLogin = async () => {
    try {
      window.location.href = KAKAO_AUTH_URI;
      const code = new URL(window.location.href).searchParams.get("code");
      console.log(code);
    } catch (error) {
      console.log(error);
    }
  };

  return (
    <LoginFrame className="">
      <a href={KAKAO_AUTH_URI}>
        <LoginImg src={kakaoImage} width="80%" alt="id" />
      </a>
    </LoginFrame>
  );
};

const LoginImg = styled.img`
  height: auto;
  cursor: pointer;
`;

const LoginFrame = styled.div`
  width: 30em;
  height: 50em;
  border: 10px;
  border-radius: 10px;
  background-color: white;

  display: flex;
  justify-content: center;
  align-items: center;
`;

const LoginButton = styled.a`
  width: 20em;
  padding: 1em 1em;
  text-align: center;
  background-color: rgb(255, 240, 194);
  color: black;
  border: 2px solid black;
`;

export default LoginPage;
