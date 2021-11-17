package com.example.parking_manager_system.Pojo;

import com.example.parking_manager_system.ModelView.ParkingSpaceViewModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 停车位
 * @author SWQXDBA
 */
@Entity
@Data
//@ApiModel(value ="车位实体类")
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ApiModelProperty(value = "车位所在区域")
    String zone;
    @ApiModelProperty(value = "车位在区域中的id")
    String idInZone;

    @ApiModelProperty(value = "出租开始时间")
    Timestamp startLeaseTime;
    @ApiModelProperty(value = "出租结束时间")
    Timestamp expirationTime;
    @ManyToOne
    @ApiModelProperty(value = "租了它的用户")
    ParkingUser leaseholder;//租户

    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;

    @Override
    public String toString() {
        return  zone +
                "区" + idInZone +"号车位";
    }
    public ParkingSpaceViewModel getViewModel(){
        ParkingSpaceViewModel m = new ParkingSpaceViewModel();
        m.id=id;
        m.leaseholder= leaseholder.getViewModel();
        m.idInZone=idInZone;
        m.zone=zone;
        m.startLeaseTime=startLeaseTime;
        m.expirationTime=expirationTime;

        return m;
    }
}
