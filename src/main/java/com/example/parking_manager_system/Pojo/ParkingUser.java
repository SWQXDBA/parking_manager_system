package com.example.parking_manager_system.Pojo;

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
public class ParkingUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String passWord;
    String phoneNumber;
    @OneToMany
    Set<ParkingSpace> parkingSpaces;

    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;
}
