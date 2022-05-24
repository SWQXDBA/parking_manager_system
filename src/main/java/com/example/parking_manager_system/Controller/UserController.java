package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.ModelView.AdminAdmitRentApplyRequestViewModel;
import com.example.parking_manager_system.ModelView.UserRegisterRequestViewModel;
import com.example.parking_manager_system.Pojo.*;
import com.example.parking_manager_system.Service.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * @author SWQXDBA
 */
@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin
public class UserController {

    AdminUserService adminUserService;
    UserService userService;
    RentApplyService rentApplyService;
    OptionLogService optionLogService;
    ParkingSpaceService parkingSpaceService;

    @Autowired
    public UserController(AdminUserService adminUserService, UserService userService, RentApplyService rentApplyService, OptionLogService optionLogService, ParkingSpaceService parkingSpaceService) {
        this.adminUserService = adminUserService;
        this.userService = userService;
        this.rentApplyService = rentApplyService;
        this.optionLogService = optionLogService;
        this.parkingSpaceService = parkingSpaceService;
    }

    @RequestMapping(value = "rent",method = RequestMethod.POST)

    @ApiOperation(value="用户发起租借请求", notes="用户发起租借请求" ,httpMethod="POST")
    public AjaxResult rent(@RequestBody AdminAdmitRentApplyRequestViewModel model,
                           HttpServletRequest request) {


        ParkingUser user = userService.getUserByRequest(request);
        if(user==null){
            return AjaxResult.error("用户未登录或者token已过期");
        }

        ParkingSpace parkingSpace = parkingSpaceService.getSpaceByInZoneAndZone(model.idInZone,model.zone);
        if(parkingSpace==null){
            return AjaxResult.error("未找到目标车位");
        }

        RentApply rentApply = new RentApply();
        rentApply.setApplyUser(user);
        rentApply.setStartRentTime(model.startLeaseTime);
        rentApply.setEndRentTime(model.expirationTime);
        rentApply.setTargetParkingSpace(parkingSpace);
        rentApplyService.applyRent(rentApply);

        return AjaxResult.success("申请已提交");
    }
    @RequestMapping(value = "login",method = {RequestMethod.POST})
    @ApiOperation(value="用户登录", notes="用户登录" ,httpMethod="POST")
    public AjaxResult login(@RequestBody ParkingUser user, HttpServletResponse response){

        String token = userService.login(user.getUserName(), user.getPassWord());
        if(token==null){
            return AjaxResult.error("用户名或密码错误");
        }
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");//所有访问都应该带上这个cookie
        response.addCookie(cookie);
     //   response.addHeader("Access-Control-Allow-Credentials","true");
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
@RequestMapping(value = "getOptionLog",method = RequestMethod.POST)
@ApiOperation(value="获取用户相关的所有日志", notes="获取用户相关的所有日志" ,httpMethod="POST")
public AjaxResult getOptionLog(HttpServletRequest request) {

    ParkingUser user = userService.getUserByRequest(request);
    if(user==null){
        return AjaxResult.error("用户未登录或者token已过期");
    }



    return AjaxResult.success(optionLogService
            //查询出全部信息
            .getAll()
            .stream()
            //过滤掉不是他的
            .filter(o -> o.getData().contains( user.getUserName()))
            //转换成视图模型
            .map(OptionLog::getViewModel)
            //转换成list
            .collect(Collectors.toList()));
}
    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ApiOperation(value="用户注册", notes="用户注册" ,httpMethod="POST")

    public AjaxResult register(@RequestBody UserRegisterRequestViewModel user, HttpServletResponse response){

        if( !userService.register(user.userName, user.password)){
            return AjaxResult.error("用户名重复!");
        }
        return AjaxResult.success("注册成功!");
    }



}
