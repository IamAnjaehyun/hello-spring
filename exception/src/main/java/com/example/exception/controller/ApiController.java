package com.example.exception.controller;

import com.example.exception.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class ApiController {

    @GetMapping("") // ?name=anjaehyun(없어도됨)
    public User get(@Valid @RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        //required = false > 필수가 아님 없어도 동작함 but null이됨
        User user = new User();
        user.setName(name);
        user.setAge(age);

        int a = 10+age;

        return user;
    }

    @PostMapping("")
    public User post(@Valid @RequestBody User user){
        System.out.println(user);
        return user;
    }

    //컨트롤러 안에 넣어두면 여기 안에서 일어나는 곳만 관여하게 된다. (글로벌로 지정했어도 컨트롤러 안에 지정해뒀으면 여기가 더 우선순위로 동작)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println("api controller");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
