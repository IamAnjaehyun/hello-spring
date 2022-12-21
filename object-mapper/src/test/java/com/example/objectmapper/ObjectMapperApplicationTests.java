package com.example.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ObjectMapperApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("---------------");

        // objectMapper 란?
        // Text JSON -> Object
        // Object -> Text JSON

        // 여태까지 한 것
        // controller request json(text) -> object
        // request object -> json(text)

        var objectMapper = new ObjectMapper();

        // Object -> Text JSON
        // object mapper 가 get method 를 활용한다.
        var user = new User("steve", 10, "010-1111-2222");

        var text = objectMapper.writeValueAsString(user);

        System.out.println(text);


        // Text JSON -> Object
        // text -> object 로 바뀔 때는 default 생성자를 필요로 한다.
        var objcetUser = objectMapper.readValue(text, User.class);
        System.out.println(objcetUser);

    }

}
