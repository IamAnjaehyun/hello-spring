package com.example.client.service;

import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //http:localhost/api/server/hello
    //response
    public UserResponse hello(){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name","aaaa")
                .queryParam("age",99)
                .encode()
                .build()
                .toUri();
        System.out.println(uri.toString()); //주소가 잘 만들어졌는지 확인

        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class); //uri에 요청을 시키고 문자열로 받음 실행되면 client -> server 붙음
        ResponseEntity<UserResponse> result= restTemplate.getForEntity(uri, UserResponse.class); //json 받기 위함

        System.out.println(result.getStatusCode()); //200OK
        System.out.println(result.getBody()); //hello server

        return result.getBody();
    }
}
