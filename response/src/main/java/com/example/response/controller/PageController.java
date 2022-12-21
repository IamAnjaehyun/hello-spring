package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {
    @RequestMapping("/main")
    public String main(){
        return "main.html";
    }
    //ResponseEntity

    //되도록 페이지 컨트롤러에서 리스폰스바디 안내림
//    @ResponseBody
//    @GetMapping("/user")
//    public User user(){
//        var user = new User();
//        user.setName("steve");
//        user.setAddress("패스트 캠퍼스");
//        return user;
//    }
}
