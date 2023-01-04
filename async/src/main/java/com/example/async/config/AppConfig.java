package com.example.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Executable;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AppConfig {

    @Bean("async-thread")//ThreadPool 지정 -> 쓰려면 좀더 공부하기 but 그냥 SpringMvc써도됨
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);
        //코어 10개 다쓰면 queue에 들어가고 queue 꽉차면 다시 core 또 다시 queue *어려웡*
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");

        return threadPoolTaskExecutor;
    }
}
