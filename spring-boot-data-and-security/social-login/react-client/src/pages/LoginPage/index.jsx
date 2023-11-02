import React from "react";
import styled from "styled-components";

const LoginPage = () => {
  return (
    <LoginFrame className="">
      <LoginButton>카카오톡 로그인 시작</LoginButton>
    </LoginFrame>
  );
};

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

const LoginButton = styled.button`
  width: 20em;
  padding: 1em 1em;
  text-align: center;
  background-color: rgb(255, 240, 194);
  color: black;
  border: 2px solid black;
`;

export default LoginPage;
