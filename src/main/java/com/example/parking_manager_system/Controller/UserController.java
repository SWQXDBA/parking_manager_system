package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.Pojo.AjaxResult;
import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Pojo.ParkingUser;
import com.example.parking_manager_system.Pojo.RentApply;
import com.example.parking_manager_system.Service.AdminUserService;
import com.example.parking_manager_system.Service.ParkingSpaceService;
import com.example.parking_manager_system.Service.RentApplyService;
import com.example.parking_manager_system.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    UserService userService;
    @Autowired
    RentApplyService rentApplyService;

    @Autowired
    ParkingSpaceService parkingSpaceService;
    @RequestMapping("rent")
    public AjaxResult rent(@RequestParam(name = "parkId") long targetId, @RequestParam(name = "startTime") Timestamp startTime, @RequestParam(name = "endTime") Timestamp endTime,
                           HttpServletRequest request) {
        ParkingUser user = userService.getUserByRequest(request);
        if(user==null){
            return AjaxResult.error("用户未登录或者token已过期");
        }
        ParkingSpace parkingSpace = parkingSpaceService.getSpaceById(targetId);
        if(parkingSpace==null){
            return AjaxResult.error("未找到目标车位");
        }

        RentApply rentApply = new RentApply();
        rentApply.setApplyUser(user);
        rentApply.setStartRentTime(startTime);
        rentApply.setEndRentTime(endTime);
        rentApply.setTargetParkingSpace(parkingSpace);
        rentApplyService.applyRent(rentApply);
     /*   if (!parkingSpaceService.rentOut(user,parkingSpace,startTime,endTime)) {
            return AjaxResult.error("租借失败!请检查目标车位是否已有主人");
        }*/
        return AjaxResult.success("申请已提交");
    }

    @RequestMapping("login")
    public AjaxResult login(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password , HttpServletResponse response){
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
