package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut01(){}

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private  void enableDecode(){}

    @Before("cut01() && enableDecode()")
    // 전은 디코드을 해서 들이고
    public void before01(JoinPoint joinPoint) throws UnsupportedEncodingException {
        // 어떻게 객체를 찾아서 바꿔줄 것인가
        // 아까 했었던 argument 를 찾으면 된다.
        // 이는 명확하게 특정 객체이거나 특정 변수이면 된다.
        Object[] args = joinPoint.getArgs();
        for(Object arg : args){
            if(arg instanceof User){
                // 내가 원하는 Object 를 찾았으면 값을 바꿔준다.
                // 디코드 과정 : User 라는 클래스로 형변환을 시키고 -> user
                // user.getEmail() 은 기존에 인코딩 되어있던 것
                // email 은 디코딩 한 결과물을 저장한 곳
                // - new String(~) 에서 ~ 자리에 바이트 배열을 입력 받을 수 있다.
                User user = User.class.cast(arg);
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email),"UTF-8");
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut01() && enableDecode()", returning = "returnObj01")
    // 후는 인코딩을 해서 내보낸다
    public void afterReturn01(JoinPoint joinPoint, Object returnObj01){
        if(returnObj01 instanceof User){
            // 디코딩 과정과 정 반대로 진행
            // 마찬가지로 User 클래스로 형변환을 이루고 -> user
            // email 이라는 평문을 인코딩하여 base64Email 에 담은 걸 볼 수 있다.
            User user = User.class.cast(returnObj01);
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }

    }

    // 실제로 위의 예제를 많이 사용하진 않지만,
    // 암호화라든지 특정 회사라던지 파트더 회사가 ~ 해주세요 하는 것
    // 비지니스 로직(=컨트롤러?)
    // 서비스 로직 앞에 간단히 어노테이션을 기입하여 AOP 적용 가능
}
