package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.Pojo.AjaxResult;
import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Service.ParkingSpaceService;
import com.example.parking_manager_system.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
@Slf4j
public class ParkingSpaceController {
    @Autowired
    UserService userService;
    @Autowired
    ParkingSpaceService parkingSpaceService;
    @RequestMapping("login")
    public AjaxResult login(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password , HttpServletResponse response){
        String token = userService.login(userName, password);
        if(token==null){
            return AjaxResult.error("用户名或密码错误");
        }
        response.addCookie(new Cookie("token",token));
        return AjaxResult.success();
    }
}
