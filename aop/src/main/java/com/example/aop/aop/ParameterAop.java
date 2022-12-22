package com.example.aop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

//aop 로 로그 남길 수 있음
@Aspect
@Component
public class ParameterAop {
    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut(){}

    //@Before("cut()")
    public void before(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("메서드 이름 출력");
        System.out.println(method.getName()); // 메서드 이름 출력


        Object[] args = joinPoint.getArgs(); //매개변수들의 배열
        for(Object obj : args){
            System.out.println("method : "+obj.getClass().getSimpleName()); //타입 가져옴
            System.out.println("value : "+ obj);
        }
        System.out.println("위에꺼 before ----------------------");

    }

    //@AfterReturning(value = "cut()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("after returning ----------------------");
        System.out.println("return obj : " + returnObj);
    }

}
