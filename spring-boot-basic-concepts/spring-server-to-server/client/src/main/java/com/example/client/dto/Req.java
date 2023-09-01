package com.example.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 현업에서 주로 사용하는 형태의 server request 형식
// T 는 제너릭 타입
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Req<T> {
    private Header header;
    private T rBody;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Header {
        private String responseCode;
    }
}
