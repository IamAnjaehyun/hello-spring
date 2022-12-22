package com.company.ioc;

public class Main {
    public static void main(String[] args) {
        String url = "www.naver.com/books/it?pate=10&size=20&name=spring-boot";

        //url encoding
        Encoder encoder = new Encoder(new UrlEncoder());
        //Base 64 encoding
        //Encoder encoder = new Encoder(new Base64Encoder());
        String result = encoder.encode(url);
        System.out.println(result);

    }
}