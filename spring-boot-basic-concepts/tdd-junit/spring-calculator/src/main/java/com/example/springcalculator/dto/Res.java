package com.example.springcalculator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Res {
    private int result;
    // json 에 depth 을 주는 것
    private Body response;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Body{
        private String resultCode = "OK";
    }

}
