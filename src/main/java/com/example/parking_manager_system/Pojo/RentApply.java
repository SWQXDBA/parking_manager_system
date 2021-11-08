package com.example.parking_manager_system.Pojo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
/**
 * 租赁申请 用户发起一个申请 需要管理员批准
 * @author SWQXDBA
 */
@Data
@Entity
public class RentApply {
    @Id
    Long id;
    /**
     * 发起申请的用户
     */
    @ManyToOne
    ParkingUser applyUser;
    //想要租借的停车场

    /**
     *  租借开始时间
     */

    Timestamp startRentTime;

    /**
     * 租借停止时间
     */
    Timestamp endRentTime;

    /**
     *  想要租赁的停车位
     */
    @ManyToOne
    ParkingSpace targetParkingSpace;

    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;

    @Override
    public String toString() {
        return
                "申请用户: " + applyUser +
                ", 目标停车位: " + targetParkingSpace ;
    }
}
