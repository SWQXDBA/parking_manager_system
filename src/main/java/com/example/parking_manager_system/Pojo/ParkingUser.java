package com.example.parking_manager_system.Pojo;

import com.example.parking_manager_system.ModelView.ParkingUserViewModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 系统的普通用户（租户）
 * @author SWQXDBA
 */
@Entity
@Data
//@ApiModel(value ="用户实体类")
public class ParkingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "唯一标识id")
    Long id;
    @ApiModelProperty(value = "用户名")
    String userName;
    @ApiModelProperty(value = "密码")
    String passWord;
    @ApiModelProperty(value = "联系方式")
    String phoneNumber;
    @OneToMany
    @ApiModelProperty(value = "他所租借的所有车位")
    Set<ParkingSpace> parkingSpaces;

    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;

    @Override
    public String toString() {
        return "用户名:" + userName +
                ", 联系方式:" + phoneNumber ;
    }
    public ParkingUserViewModel getViewModel(){
        ParkingUserViewModel m = new ParkingUserViewModel();
        m.userName=userName;
        m.phoneNumber=phoneNumber;
        return m;
    }
}
