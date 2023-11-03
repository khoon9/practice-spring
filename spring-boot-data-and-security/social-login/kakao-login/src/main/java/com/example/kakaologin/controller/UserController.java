package com.example.kakaologin.controller;

import com.example.kakaologin.model.OauthToken;
import com.example.kakaologin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController //(1)
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService; //(2)

    // 프론트에서 인가코드 받아오는 url
    @GetMapping("/oauth/token") // (3)
    public OauthToken getLogin(@RequestParam("code") String code) { //(4)

        // 넘어온 인가 코드를 통해 access_token 발급 //(5)
        OauthToken oauthToken = userService.getAccessToken(code);

        return oauthToken;
    }

}