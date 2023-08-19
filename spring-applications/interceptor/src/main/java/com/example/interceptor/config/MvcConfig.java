package com.example.interceptor.config;

import com.example.interceptor.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {
    // @RequiredArgsConstructor 을 통해서, final 로 선언된 객체들을 생성자에서 주입받을 수 있도록 해준다.
    // 즉, lombok 에 의해서 MvcConfig(AuthInterceptor) 생성자가 자동 생성된 것
    // 유의 : @Autowired 를 통해 자체적으로 받을 수도 있지만, 순환참조가 발생할 수도 있어서 요즘엔 생성자로 받는 추세이다.
    // 여기서 AuthInterceptor 은 단 등록하고자 하는 Interceptor 클래스를 의미한다.
    private final AuthInterceptor authInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
        // .addPathPatterns("/api/private/*", ...) 를 다음과 같이 작성할 경우,
        // 해당 주소에 대해서만 authInterceptor 가 동작하도록 한다는 것이다.
        // 보통 Annotation 을 사용하여 인증을 하거나
        // .addPathPatterns(...) 을 사용하여 인증을 한다.
        // registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*")

        // registry.addInterceptor(...); 을 나열할 경우, 인터셉트가 나열된 순서대로 수행된다.
        // 그렇기에 인증 절차를 깊이있게 구성이 가능하다.
    }
}
