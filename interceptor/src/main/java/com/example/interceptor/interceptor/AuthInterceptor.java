package com.example.interceptor.interceptor;

import com.example.interceptor.annotation.Auth;
import com.example.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
                .query(request.getQueryString())
                .build()
                .toUri();

        //handler에는 여러 정보들이 들어가있음
        log.info("request url : {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("has annotation : {}", hasAnnotation);

        // 나의 서버는 모두 public 으로 동작을 하는데
        // 단! Auth 권한을 가진 요청에 대해서는 세션, 쿠키
        if (hasAnnotation) {
            //권한을 가지고 있는가(권한 체크)
            String query = uri.getQuery();
            if (query.equals("name=steve")){
                return true;
            }
            throw new AuthException();
        }
        return true;
    }

    private boolean checkAnnotation(Object handler, Class clazz) {

        // resoruce javascript, html
        if (handler instanceof ResourceHttpRequestHandler) {
            return true; //통과
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {
            //어노테이션 갖고있으면 true
            //Auth 있음 true
            return true;
        }
        return false;
    }
}