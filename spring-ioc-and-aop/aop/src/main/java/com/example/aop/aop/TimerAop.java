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
    // @annotation(com.example.aop.annotation.Timer) :
    // ~annotation 패키지 내의 Timer 클래스 내의 메소드에만 걸겠다는 의미
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

    // 미리 메소드가 실행되기 전에 특정한 값을 바꾸는 방법
    // 암호화된 값을 배운다던지, 필터, 인터셉터 등 변환을 하고자 할 때
    // 톰켓 자체에서 한 번 바디를 읽으면 더이상 읽을 수 없도록 막아놨기에
    // 변환을 하기가 어렵다. 그런데, AOP 같은 구간에서는 필터와 인터셉터를 지나서
    // 값 자체가 객체화 되었기에 그 값을 변환해주거나 AOP 에서 특정한 객체를 넣어줄 수 있다.
    // 그렇기에 외부에서 특정 암호화된 파일이나 필드가 들어왔을 때 코드에서 복호화하는 게 아니라
    // 이미 AOP 에서 복호화를 이루도록 한 상태에서 들어오게할 수 있다.
    // 또는 그걸 밖으로 내보낼 때, 내부 코드에서는 일반적으로 코딩을 하지만, 특정 회원사에게
    // 보낸다거나 특정 서버에게 보낼 때 AOP 구간에서 그걸 변경시켜 주도록 할 수 있다.
    //


}
// Component 클래스 단위로 Bean을 등록 할 수 있다.
// Bean은 클래스에 붙일 수 없다. 메소드에 붙일 수 있다.
// Configuration은 하나의 클래스에 Bean 여러개를 등록할 수 있다.