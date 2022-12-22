package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;


//spring 은 new 로 생성안하고 권한을 다 spring 한테 넘김 > 제어의 역전(ioc)
@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();

//        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
//        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

        Encoder encoder = context.getBean("urlEncode",Encoder.class);
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        String result = encoder.encode(url);
        System.out.println(result);

//        encoder.setIEncoder(urlEncoder);
//        result = encoder.encode(url);
//        System.out.println(result);
    }
}

@Configuration //여러가지 bean을 담겠다
class AppConfig{

    @Bean("base64Encode") //이름 같은 Encoder bean 두개니까 이름 붙여줌
    public Encoder encoder(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder encoder(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
}
