package com.example.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// ElementType.TYPE와 ElementType.METHOD 에 타겟을 걸겠다는 의미이다.
// RetentionPolicy.RUNTIME는 보존정책을 RUNTIME애 걸겠다는 의미
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Timer {
}
