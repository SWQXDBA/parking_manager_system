package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.Pojo.AjaxResult;
import com.example.parking_manager_system.Service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("admin")
@Slf4j
public class AdminController {
    @Autowired
    AdminUserService adminUserService;

    @RequestMapping("login")
    public AjaxResult adminlogin(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password , HttpServletResponse response){
        String token = adminUserService.adminLogin(userName, password);
        if(token==null){
            return AjaxResult.error("用户名或密码错误");
        }
        response.addCookie(new Cookie("token",token));
        return AjaxResult.success();
    }
}
