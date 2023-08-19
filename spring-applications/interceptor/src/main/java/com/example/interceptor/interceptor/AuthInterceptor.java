package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Intercepter 또한 request body 와 response body 를 읽고자 할 때
        // 캐싱 처리를 해두어 버퍼를 다뤄야지 정상적인 동작 설계가 가능하다
        // 만약 Filter 에서 chin.doFilter 메개변수로 request 와 response 를 ContentCaching~Wrapper 타입으로 변환한 이후 넘겼다면
        // (ContentCaching~Wrapper) 으로 response/request 를 형변환하여 바로 사용이 가능하다
        // Filter 에서 캐싱 형변환을 안 했다면 Servlet~ 자료형일 것이다.
        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        log.info("request url : {}", url);

        // 권한 체크
        // interceptor 은 spring context 에서 관리되고 있기에
        // 어떠한 클래스로 가는지, 어떠한 컨트롤러로 가는지 등을 이러한 어노테이션을 통해서도 관리가 가능하다.
        // filter 는 이러한 handler 가 없기 때문에 이렇게 인증 절차를 구성하는 것이 불가능하다
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // 나의 서버는 모두 public 으로 동작을 하는데
        // 단! Auth 권한을 가진 요청에 대해서는 세션, 쿠키,
        // 원래는 쿠키나 세션을 체크하는데, 이번 경우에는 시간관계상 아래와 같이 체크
        if(hasAnnotation){
            // 권한 체크
            String query = uri.getQuery();
            log.info("query : {}", query);
            // 쿼리의 내용이 동일하면 통과
            if (query.equals("name=steve")){
                return true;
            }
            // return false;
            // 예외 처리를 위한 클래스를 동작시켜야 클라이언트가 권한 없음을 인지할 수 있다.
            // 여기서는 AOD 를 사용했다.
            throw new AuthException();
        }

        // Interceptor 의 핵심을 return false; 이다.
        // return false 이면 동작하지 않는다는 의미
        // 즉, 인터셉터 이후 controller 까지 못 간다는 이미.
        return true;
    }
    private boolean checkAnnotation(Object handler, Class clazz){
        //  resource javascript, html 같은 경우에는 통과시켜줘야 한다.
        if (handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        // annotation check
        // 조금 더 알아보고 싶다면, HandlerMethod 의 기능에 대해서 별도로 알아보는 것도 좋다.
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // handlerMethod 에 class 가 붙어있는가, 또는 handlerMethod 의 Bean 의 Annotation 이 달려있는지 확인

        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // Auth annotation 이 있을 때에는 true
            return true;
        }
        return false;
    }
}
