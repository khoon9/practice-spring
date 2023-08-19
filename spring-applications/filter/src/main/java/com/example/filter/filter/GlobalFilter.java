package com.example.filter.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
@Slf4j
@WebFilter(urlPatterns = "/api/user/*")
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
        // 필터단에서는 request 와 response 에 대해서 변경시켜줄 수 있다.
        // HttpServletRequest 는 ServletRequest 를 상속받은 클래스이기에 인터페이스만 바꿔서 사용하면 된다.
        // ContentCaching~Wrapper 의 경우, 생성했을 때는 CashedContent 에 단지 문자열 길이만을 저장한다.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);



        // 스프링 내부 로직으로 들어가서 ContentCaching~Wrapper 의 메소드가 실행되어야
        // CashedContent 가 내용을 품기 때문에 '후처리' 부분에서 다뤄야 한다.
        chain.doFilter(httpServletRequest, httpServletResponse);

        // 후처리

        // request
        String requestContent = new String(httpServletRequest.getContentAsByteArray());
        String url = httpServletRequest.getRequestURI();
        log.info("request url : {}, request body : {}", url, requestContent);

        // response
        String responseContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();
        // 읽었던 거 복구
        httpServletResponse.copyBodyToResponse();
        log.info("response status : {}, response body : {}", httpStatus, responseContent);
    }
}
