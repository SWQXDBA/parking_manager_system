package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.ModelView.ParkingSpaceUpdateViewModel;
import com.example.parking_manager_system.ModelView.ParkingSpaceViewModel;
import com.example.parking_manager_system.Pojo.AdminUser;
import com.example.parking_manager_system.Pojo.AjaxResult;
import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Pojo.ParkingUser;
import com.example.parking_manager_system.Service.AdminUserService;
import com.example.parking_manager_system.Service.ParkingSpaceService;
import com.example.parking_manager_system.Service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("parkingSpace")
@Slf4j
@CrossOrigin
public class ParkingSpaceController {

    @Autowired
    ParkingSpaceService parkingSpaceService;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserService adminUserService;

    @RequestMapping(value = "getAll", method = RequestMethod.POST)
    @ApiOperation(value = "获取所有停车位", notes = "获取所有停车位", httpMethod = "POST")
    public List<ParkingSpaceViewModel> getAll() {
        List<ParkingSpace> entities = parkingSpaceService.getAllSpacesSaveData();
        return entities.stream().map(ParkingSpace::getViewModel).collect(Collectors.toList());
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "更新车位信息", notes = "编辑车位信息", httpMethod = "POST")
    public AjaxResult update(HttpServletRequest request,@RequestBody ParkingSpaceUpdateViewModel viewModel) {
        AdminUser user = adminUserService.getUserByRequest(request);
        if(user==null){
            return AjaxResult.error("管理员未登录或者token已过期");
        }
        if (parkingSpaceService.update(viewModel)) {
          return   AjaxResult.success("修改成功");
        }else{
          return   AjaxResult.error("修改失败 请检查用户名是否正确");
        }

    }

    @RequestMapping(value = "getByUser", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户的所有停车位", notes = "获取所有停车位", httpMethod = "POST")
    public AjaxResult getByUser(HttpServletRequest request) {
        ParkingUser user = userService.getUserByRequest(request);
        if (user == null) {
            return AjaxResult.error("用户未登录");
        }
        List<ParkingSpace> entities = parkingSpaceService.getSpaceByUser(user);
        //转换成视图模型返回
        return AjaxResult.success(entities.stream().map(ParkingSpace::getViewModel).collect(Collectors.toList()));
    }
}
