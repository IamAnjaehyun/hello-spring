package com.example.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base74Encoder")
// bean 으로 등록 스프링이 객체로 관리해달라 > 권한 넘김 > 제어의 역전
public class Base64Encoder implements IEncoder{
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
