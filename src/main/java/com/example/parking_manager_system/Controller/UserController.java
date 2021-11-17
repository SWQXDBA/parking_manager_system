package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.Pojo.AjaxResult;
import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Pojo.ParkingUser;
import com.example.parking_manager_system.Pojo.RentApply;
import com.example.parking_manager_system.Service.AdminUserService;
import com.example.parking_manager_system.Service.ParkingSpaceService;
import com.example.parking_manager_system.Service.RentApplyService;
import com.example.parking_manager_system.Service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "rent",method = RequestMethod.POST)
    @ApiOperation(value="用户发起租借请求", notes="用户发起租借请求" ,httpMethod="POST")
    public AjaxResult rent(@RequestParam(name = "parkId") long targetId,  Timestamp startRentTime,  Timestamp endRentTime,
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
        rentApply.setStartRentTime(startRentTime);
        rentApply.setEndRentTime(endRentTime);
        rentApply.setTargetParkingSpace(parkingSpace);
        rentApplyService.applyRent(rentApply);
     /*   if (!parkingSpaceService.rentOut(user,parkingSpace,startTime,endTime)) {
            return AjaxResult.error("租借失败!请检查目标车位是否已有主人");
        }*/
        return AjaxResult.success("申请已提交");
    }
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value="用户登录", notes="用户登录" ,httpMethod="POST")
    public AjaxResult login(@RequestBody ParkingUser user, HttpServletResponse response){

        String token = userService.login(user.getUserName(), user.getPassWord());
        if(token==null){
            return AjaxResult.error("用户名或密码错误");
        }
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");//所有访问都应该带上这个cookie

        response.addCookie(cookie);
        return AjaxResult.success();
    }
/*
    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value="用户登录", notes="用户登录" ,httpMethod="POST")
    public AjaxResult login(@RequestBody Map<String,String> map, HttpServletResponse response){
        log.error("userName:"+map.get("userName"));
        log.error("password:"+map.get("passWord"));
        String token = userService.login(map.get("userName"), map.get("passWord"));
        if(token==null){
            return AjaxResult.error("用户名或密码错误");
        }
        response.addCookie(new Cookie("token",token));
        return AjaxResult.success();
    }
*/

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ApiOperation(value="用户注册", notes="用户注册" ,httpMethod="POST")

    public AjaxResult register(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password , HttpServletResponse response){

        if( !userService.register(userName, password)){
            return AjaxResult.error("用户名重复");
        }
        return AjaxResult.success();
    }



}
