package com.example.parking_manager_system.Interceptor;


import com.example.parking_manager_system.Dao.UserDao;
import com.example.parking_manager_system.Exceptions.UnLoginException;
import com.example.parking_manager_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆验证 验证是否有token 如果有 则验证token的有效性
 * @author SWQXDBA
 */

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        Cookie tokenCookie = WebUtils.getCookie(request, "token");
        if(tokenCookie==null){
            return false;
        }
        String token = tokenCookie.getValue();
        if(userService.checkToken(token)){
            return true;
        }else{
            throw  new UnLoginException();
        }
    }
}
