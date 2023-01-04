package com.example.client.service;

import com.example.client.dto.UserRequest;
import com.example.client.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    //http://localhost:9090/api/server/hello
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
        //여기에 있는 GET은 http method의 GET이고 entity로 가져오겠다 이다!!! (가져오다의 GET이 아님)
//        String result = restTemplate.getForObject(uri, String.class); //uri에 요청을 시키고 문자열로 받음 실행되면 client -> server 붙음
        ResponseEntity<UserResponse> result= restTemplate.getForEntity(uri, UserResponse.class); //json 받기 위함

        System.out.println(result.getStatusCode()); //200OK
        System.out.println(result.getBody()); //hello server

        return result.getBody();
    }

    public UserResponse post(){
        //http://localhost:9090/api/server/user/{userId}/name/{userName} (실제주소는 안이럼)
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "anjh") //순서대로 들어감
                .toUri();

        System.out.println(uri);
        //주소 만들고 바디데이터 오브젝트로 만들면 오브젝트 매퍼가 json으로 바꿔주고 resttemplate 가 httpbody에 json으로 쏜다
        //http body -> object만 보냄 -> object mapper -> json > rest template -> http body json
        UserRequest req = new UserRequest();
        req.setName("anjh");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);
//        ResponseEntity<String> response = restTemplate.postForEntity(uri, req, String.class); //어떤식으로 데이터 내려줄지 모르기 떄문에 이런식으로 받아도 됨

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }
}
