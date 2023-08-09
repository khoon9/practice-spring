package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// html 페이지의 리소스를 찾게 된다.
@Controller
public class PageController {

    // REST 를 사용하지 않으므로 기본적으로 자유롭다.
    @RequestMapping("/main")
    public String main(){
        // return 자체가 String 이 될 경우, 자동으로 resource 에 있는 것을 찾아간다.
        return "main.html";
    }

    // 여기서 JSON 을 어떻게 내려줄지에 대한 탐구
    // ResponseEntity

    // 객체 자체를 return 했을 때, resource 를 찾지 않고 ResponseBody 를 내리게 된다.
    @ResponseBody
    @GetMapping("/user")
    public User user(){
        User user = new User();
        user.setName("steve");
        user.setAddress("fc");
        return user;
    }

}
