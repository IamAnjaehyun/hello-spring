package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@Component //스프링에 의해 bean으로 관리
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //전처리

        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper ((HttpServletRequest)request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse)response);

        String url = httpServletRequest.getRequestURI(); //어떠한 주소를 요청했는지

//        BufferedReader br = httpServletRequest.getReader();
//
//        br.lines().forEach(line -> {
//            log.info("url : {}, line : {} ", url, line);
//        }); //누구던지 한번 read를 해버리면 클라이언트에서 요청이 오는 것을 더이상 읽을 수 없다.

        chain.doFilter(httpServletRequest, httpServletResponse); //동작하고 나면 response 만들어짐
        //doFilter 끝난 후에 request를 읽자!

        //후처리
        //req
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("request url : {}, responseBody : {}", url, reqContent);


        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();

        httpServletResponse.copyBodyToResponse();

        log.info("response status : {}, responseBody : {}", httpStatus, resContent);
        //후처리에서 모든 정보를 기록
    }
}
