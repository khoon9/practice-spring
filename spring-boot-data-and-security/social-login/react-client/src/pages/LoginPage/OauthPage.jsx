import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const OauthPage = () => {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get("code");
  console.log("받은 인증 코드", code);

  useEffect(() => {
    (async () => {
      try {
        // api 는
        const res = await axios.get(`api/code=${code}`);
        console.log("받은 response 값", res);
        const token = res.headers.authorization;
        console.log("token", token);
        alert("성공함");
        navigate("/");
      } catch (error) {
        console.log(error);
        alert("실패함");
        navigate("/");
      }
    })();
  }, []);

  return <div>OauthPage</div>;
};

export default OauthPage;
