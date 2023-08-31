package com.example.restaurant.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pages")
public class PageController {
    // 특정한 html 모델 뷰로 보낼 때 사용
    @GetMapping("/main")
    public ModelAndView main(){
        return new ModelAndView("main");
    }
}
