package com.example.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AppConfig {

    //
    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // Thread 할당량의 최대 증가치가 100 이라는 의미
        threadPoolTaskExecutor.setMaxPoolSize(100);
        // 기본 Thread 할당량. Queue 를 넘어가는 요청이 발생할 경우 Thread 할당량 10씩 증가
        threadPoolTaskExecutor.setCorePoolSize(10);
        // 기본 Thread 할당량 + 1~10 에서 1~10 부분이 Queue
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        return threadPoolTaskExecutor;
    }

}
