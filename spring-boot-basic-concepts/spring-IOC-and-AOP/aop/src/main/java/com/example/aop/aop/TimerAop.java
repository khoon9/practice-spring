package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut01(){}
    // 위의 포인트 컷 이외에 타이머로서 동작을 할 수 있도록 어노테이션에 대한 제약을 하나 더 걸기 위해
    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private  void enableTimer(){}

    // 시간을 공유하기 위해 Around 를 사용. before와 after은 시간 측정 공유 불가
    // Around에 쓰는 파라미터 : ProceedingJoinPoint :
    @Around("cut01() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        // joinPoint.proceed() 실행 전
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // joinPoint.proceed() 호출은, 실질적인 메소드의 호출과 동일
        // 실행이 이뤄질 경우 그 호출 반환값을 result 로 저장하겠다는 의미이다.
        Object result = joinPoint.proceed();

        // joinPoint.proceed() 실행 후
        stopWatch.stop();

        // 메소드 수행 시간 반환과 동일
        System.out.println("total time : "+stopWatch.getTotalTimeSeconds());
    }
}