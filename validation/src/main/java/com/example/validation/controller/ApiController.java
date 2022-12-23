package com.example.validation.controller;

import com.example.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    @PostMapping("/user") //user를 받아서 user를 출력하는 echo
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult) { //valid 어노테이션 붙으면 valid 붙여줘야
//        bindingResult == validation에 대한 에러가 들어감

        if (bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : " + field.getField());
                System.out.println("message : " + message);

                sb.append("field : " + field.getField());
                sb.append("message : " + message);

            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        System.out.println(user);
        // 여기까지 옳았다면 로직실행되면 됨

//        if (user.getAge() >= 90) { //나이가 90살보다 크면 에러 던짐
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
//        }

//        if (user.getPhoneNumber() == "xxx-xxxx-xxxx") { //폰번호가 저 형식이 아니면 에러 던짐
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
//        }
        return ResponseEntity.ok(user);
    }
}
