package com.example.parking_manager_system.Interceptor;


import com.example.parking_manager_system.Dao.UserDao;
import com.example.parking_manager_system.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = (String) request.getSession(false).getAttribute("token");
        if(token==null){
            return false;
        }
        return userService.containsToken(token);
    }
}
