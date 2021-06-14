package com.koreait.spring;

import com.koreait.spring.user.UserEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class AuthCheckInterceptor implements HandlerInterceptor {

    private final String[] needLoginUriArr = {"/board/writeMod", "/board/favList", "/user/profile"};

    //Controller로 보내기 전에 처리
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true; //false하면 값이 안 감..?
    }

    //Controller의 handler가 끝나면 처리
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        System.out.println("uri:"+uri);

        if (Arrays.asList(needLoginUriArr).contains(uri)) {
            UserEntity loginUser = (UserEntity) httpServletRequest.getSession().getAttribute("loginUser");
            if(loginUser == null){
                modelAndView.setViewName("/user/needLogin");
            }
        }
    }

    //view까지 처리가 끝난 후에 처리
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
