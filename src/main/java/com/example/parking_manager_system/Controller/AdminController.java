package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.Pojo.AdminUser;
import com.example.parking_manager_system.Pojo.AjaxResult;
import com.example.parking_manager_system.Pojo.OptionLog;
import com.example.parking_manager_system.Pojo.RentApply;
import com.example.parking_manager_system.Service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("admin")
@Slf4j
@Api("管理员接口")
public class AdminController {
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    UserService userService;
    @Autowired
    RentApplyService rentApplyService;

    @Autowired
    OptionLogService optionLogService;
    @Autowired
    ParkingSpaceService parkingSpaceService;
    @RequestMapping(value = "admit",method = RequestMethod.POST)
    @ApiOperation(value="批准租借请求", notes="批准用户的租借请求" ,httpMethod="POST")
    public AjaxResult rent(@RequestParam(name = "applyId") long rentApplyId,
                           HttpServletRequest request) {
        AdminUser user = adminUserService.getUserByRequest(request);
        if(user==null){
            return AjaxResult.error("管理员未登录或者token已过期");
        }
        RentApply rentApply = rentApplyService.getRentById(rentApplyId);
        if(rentApply==null){
            return AjaxResult.error("要审批的租借请求未找到");
        }

        parkingSpaceService.rentOut(rentApply.getApplyUser(),rentApply.getTargetParkingSpace(),rentApply.getStartRentTime(),rentApply.getEndRentTime());

        OptionLog log = new OptionLog();
        log.setAdminUser(user);
        log.setData("管理员批准了用户申请  "+rentApply);

        rentApplyService.deleteRentApply(rentApply);
        optionLogService.save(log);
     /*   if (!parkingSpaceService.rentOut(user,parkingSpace,startTime,endTime)) {
            return AjaxResult.error("租借失败!请检查目标车位是否已有主人");
        }*/
        return AjaxResult.success("批准成功!");
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value="管理员登录", notes="管理员登录" ,httpMethod="POST")
    public AjaxResult adminlogin(@RequestParam(name = "username") String userName, @RequestParam(name = "password") String password , HttpServletResponse response){
        String token = adminUserService.adminLogin(userName, password);
        if(token==null){
            return AjaxResult.error("用户名或密码错误");
        }
        response.addCookie(new Cookie("token",token));
        return AjaxResult.success();
    }

}
