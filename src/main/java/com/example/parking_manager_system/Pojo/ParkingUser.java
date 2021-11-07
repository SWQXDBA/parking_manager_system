package com.example.parking_manager_system.Pojo;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
