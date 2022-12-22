package com.example.aop.aop;

import com.example.aop.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.aop.controller..*.*(..))")
    private void cut() {
    }

    @Pointcut("@annotation(com.example.aop.annotation.Decode)")
    private void enableDecode() {
    }
    //인코딩 된 이메일을 디코딩해서 내보냄
    @Before("cut() && enableDecode()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            // instanceof -> 상속통해 만들어진 자식 객체가 여러 타입인 경우 특정 클래스가 맞는지 확인
            if (arg instanceof User) {
                User user = User.class.cast(arg);
                String base64Email = user.getEmail(); // 인코딩 된 이메일

                String email = new String(Base64.getDecoder().decode(base64Email)); //디코딩
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "cut() && enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        if(returnObj instanceof User){
            User user = User.class.cast(returnObj);
            String email = user.getEmail(); // 평문 이메일을

            String base64Email = Base64.getEncoder().encodeToString(email.getBytes()); // 평문이메일을 인코딩해서
            user.setEmail(base64Email);// set해줌
        }
    }

}
