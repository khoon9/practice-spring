package com.example.put.controller;

import com.example.put.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping("/put01")
    public PutRequestDto putApi01(@RequestBody PutRequestDto requestDto){
        System.out.println(requestDto);
        return requestDto;
    }

    @PutMapping("/put02/{userId}")
    public PutRequestDto putApi02(
            @RequestBody PutRequestDto requestDto,
            @PathVariable(name = "userId") Long id)
    {
        System.out.println(requestDto);
        System.out.println(id);
        return requestDto;
    }
}
