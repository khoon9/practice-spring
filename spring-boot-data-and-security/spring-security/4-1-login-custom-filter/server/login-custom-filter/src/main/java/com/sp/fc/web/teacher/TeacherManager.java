package com.sp.fc.web.teacher;

import com.sp.fc.web.student.Student;
import com.sp.fc.web.student.StudentAuthenticationToken;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

@Component
public class TeacherManager implements AuthenticationProvider, InitializingBean {
    // AuthenticationProvider 가 관리하는 대상 리스트. 원칙적으로는 DB 에서 가져오지만
    // 이번엔 간소한 형태로 구성
    private HashMap<String, Student> teacherDB = new HashMap<>();

    // AuthenticationToken 을 Student AuthenticationToken 으로 발급을 받을라고 한다.
    // UsernamePasswordAuthenticationToken 으로 인증했기에 포장 형태를 이와 같이 생성했다.
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        if(teacherDB.containsKey(token.getName())){
            Student teacher = teacherDB.get(token.getName());
            return StudentAuthenticationToken.builder()
                    .principal(teacher)
                    .details(teacher.getUsername())
                    .authenticated(true)
                    .build();
        }
        // 내가 처리할 수 없는 authention 에 대해서는 null 로 넘겨야 한다.
        return null;
    }

    // UsernamePasswordAuthenticationToken 형태의 토큰을 받으면 우리가 검증을 해주겠다는 의미
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == UsernamePasswordAuthenticationToken.class;
    }

    // 컴포넌트 빈이 처음 초기화되었을 때 설정할 용도로 생성
    @Override
    public void afterPropertiesSet() throws Exception {
        Set.of(
                new Student("choi", "최선생", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER"))),
                new Student("kang2", "강아지", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER"))),
                new Student("rang2", "호랑이", Set.of(new SimpleGrantedAuthority("ROLE_TEACHER")))
        ).forEach(s->
                teacherDB.put(s.getId(), s)
        );

    }
}
