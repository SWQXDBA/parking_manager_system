package com.example.parking_manager_system.ModelView;

import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Pojo.RentApply;

import java.sql.Timestamp;

/**
 * @author SWQXDBA
 */
public class AdminGetAllRentApplyResponseViewModel {
    //租借请求
    public Long rentId;

    public String rentUserName;

    public Timestamp startRentTime;

    public  Timestamp endRentTime;



//停车场原来的租借信息 用来给管理员参考
    public  String zone;

    public String idInZone;

    public Timestamp startLeaseTime;

    public  Timestamp expirationTime;

    public String leaseholderUserName ;


    public AdminGetAllRentApplyResponseViewModel(RentApply rentApply) {
        rentId = rentApply.getId();
        rentUserName = rentApply.getApplyUser().getUserName();
        startRentTime = rentApply.getStartRentTime();
        endRentTime = rentApply.getEndRentTime();

        ParkingSpace space = rentApply.getTargetParkingSpace();
        zone = space.getZone();
        idInZone = space.getIdInZone();
        startLeaseTime = space.getStartLeaseTime();
        expirationTime = space.getExpirationTime();

        if(space.getLeaseholder()!=null){
            leaseholderUserName = space.getLeaseholder().getUserName();
        }


    }
}
