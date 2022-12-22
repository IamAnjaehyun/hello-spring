package com.example.aop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/*
@Component
 -개발자가 직접 작성한 클래스를 bean 등록하고자 할 경우 사용
@Configuration + @Bean
 -외부라이브러 또는 내장 클래스를 bean 으로 등록하고자 할 경우 사용.
 -1개 이상의 @Bean 을 제공하는 클래스의 경우 반드시 @Configuraton 을 명시
*/
// bean vs componet  > bean은 메서드에서 쓸 수 있고 클래스에 달 수 없음 componet를 통해서 클래스 단위로 달 수 잇음
// configuration 은 하나에 클래스에 여러가지 빈을 쓰겠다.
// 특정 메서드의 실행시간을 찍자 !
// AOP 횡단 - 모든 메서드에 같은 기능이 들어가있음 > 그러므로 같은기능 다쓰지 말고 정의해놓고 쓰자
@Aspect
@Component
public class TimerAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {}

    @Pointcut("@annotation(com.example.aop.annotation.Timer)")
    private void enableTimer(){}

    @Around("cut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //호출되면 메서드가 실행되고 리턴타입이 있으면 오브젝트로 리턴됨 특정값이 리턴되면 오브젝트로 값이 들어감
        Object result = joinPoint.proceed();

        stopWatch.stop();

        System.out.println("total time : "+stopWatch.getTotalTimeSeconds());
    }
}
