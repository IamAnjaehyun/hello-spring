package com.example.async.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {


    @Async("async-thread") //public method에만 Async 달 수 있음
    public CompletableFuture run(){ //api 여러개를 동시에 전송을 한 다음에 그에 대한 결과를 join을 통해 받을때 사용하는게 좋음
//        hello(); 이거 동작 안함 같은클래스 내에서 같은 메서드 돌리면 async 타지 않음
        return new AsyncResult(hello()).completable();
    }

    public String hello() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "async hello";
    }
}
