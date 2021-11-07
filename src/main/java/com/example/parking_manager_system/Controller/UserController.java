package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.Pojo.AjaxResult;
import com.example.parking_manager_system.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("login")
    public AjaxResult login(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password , HttpServletResponse response){
        String token = userService.login(userName, password);
        if(token==null){
            return AjaxResult.error("用户名或密码错误");
        }
        response.addCookie(new Cookie("token",token));
        return AjaxResult.success();
    }

    @RequestMapping("adminlogin")
    public AjaxResult adminlogin(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password , HttpServletResponse response){
        String token = userService.login(userName, password);
        if(token==null){
            return AjaxResult.error("用户名或密码错误");
        }
        response.addCookie(new Cookie("token",token));
        return AjaxResult.success();
    }


    @RequestMapping("register")
    public AjaxResult register(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password , HttpServletResponse response){

        if( !userService.register(userName, password)){
            return AjaxResult.error("用户名重复");
        }
        return AjaxResult.success();
    }



}
