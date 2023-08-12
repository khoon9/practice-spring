package com.example.springioc;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// 해당 클래스의 전체흐름
// 1. 스프링 어플리케이션이 실행이 될 때, ApplicationContextProvider가 생성된다.
// 2. 이후 스프링에 의해 자동적으로 setApplicationContext 으로 의존성 주입이 이뤄진다.
// 3. 그릇에 저장해둔 context 을 getContext() 으로 꺼내다 쓰면 된다.
// 여기서, ApplicationContext 는 Bean 을 불러올 수 있는 스프링 컨테이너와 동일하게 여기면 된다.

// 여기서 set 부분은 setter와 같이 여기면 될 것 같다.
@Component
public class ApplicationContextProvider implements ApplicationContextAware {

    // 의존성 주입을 받은 걸 보관할 그릇을 생성한다.
    // 이때, 해당 클래스의 static 메소드를 통해서 context 를 불러와야 하기에 그릇을 static으로 선언.
    private static ApplicationContext context;

    // 여기서 매개변수 applicationContext 은 외부로부터 주입을 받는 부분
    // Spring 에서 알아서 주입해준다.
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }


    public static ApplicationContext getContext(){
        return context;
    }
}

