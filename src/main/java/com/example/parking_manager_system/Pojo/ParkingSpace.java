package com.example.parking_manager_system.Pojo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
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
    Date expirationTime;
    @ManyToOne
    ParkingUser leaseholder;

    @CreationTimestamp
    Timestamp createTime;
    @UpdateTimestamp
    Timestamp updateTime;
}
