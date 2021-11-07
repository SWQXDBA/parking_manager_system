package com.example.parking_manager_system.Pojo;

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
public class ParkingSpace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String zone;

    String idInZone;
    Timestamp startLeaseTime;
    Timestamp expirationTime;
    @ManyToOne
    ParkingUser leaseholder;//租户

    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;
}
