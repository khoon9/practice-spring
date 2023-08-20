package com.example.async.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

    // thread config 적용
    @Async("async-thread")
    public CompletableFuture run(){
        // 여기서 hello() 메소드, 즉 클래스 내의 메소드를 CompletableFuture 없이
        // 그냥 불러오면 쓰레드 적용이 안 된다.
        return new AsyncResult(hello()).completable();
    }
    public String hello() {
        for (int i=0; i < 10; i++){
            try{
                Thread.sleep(2000);
                log.info("thread sleep ...");
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        return "async hello";
    }
}
