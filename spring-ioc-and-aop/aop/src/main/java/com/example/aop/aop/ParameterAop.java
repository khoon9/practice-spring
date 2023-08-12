package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParameterAop {
    // 다른 특정 메소드를 지칭하고자 할 때, 인터넷을 사용하여 개별적으로 알아보는 과정이 필요함. ex) 다른 표현들
    // aop 라는 프로젝트의(-"com.example.aop"), controller 라는 패키지의(-".controller") 하위에 있는(-"..") 모든 메소드를(-"*.*(..)") 전부 aop 로 보겠다는 문구
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut01(){

    }
    // JoinPoint : 들어갈 때의 정보를 가지고 있는 객체
    @Before("cut01()")
    public void before01(JoinPoint joinPoint){
        // 실행한 메소드의 이름을 JoinPoint 타입을 사용하여 불러오는 방법
        // 아래와 같이 형변환을 하면 메소드를 가져올 수 있다.
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        // joinPoint.getArgs() : 메소드에 들어가고 있는 매개변수들의 배열을 호출
        Object[] args = joinPoint.getArgs();
        for (Object obj : args){
            System.out.println("type : " +obj.getClass().getSimpleName());
            System.out.println("value : "+obj);
        }
    }
    @AfterReturning(value = "cut01()", returning = "returnObj01")
    public  void afterReturn01(JoinPoint joinPoint, Object returnObj01){
        System.out.println("return obj");
        System.out.println(returnObj01);
    }

}
