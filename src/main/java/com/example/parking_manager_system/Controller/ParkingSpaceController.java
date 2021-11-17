package com.example.parking_manager_system.Controller;

import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Service.ParkingSpaceService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("parkingSpace")
@Slf4j
public class ParkingSpaceController {

    @Autowired
    ParkingSpaceService parkingSpaceService;
    @RequestMapping(value = "getAll",method = RequestMethod.POST)
    @ApiOperation(value="获取所有停车位", notes="获取所有停车位" ,httpMethod="POST")
    public List<ParkingSpace> getAll() {
        return parkingSpaceService.getAllSpacesSaveData();
    }

}
