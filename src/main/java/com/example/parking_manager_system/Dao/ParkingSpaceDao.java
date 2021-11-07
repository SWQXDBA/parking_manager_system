package com.example.parking_manager_system.Dao;

import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Pojo.ParkingUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceDao extends CrudRepository<ParkingSpace,Long> {
    List<ParkingSpace> getParkingSpacesByZone(String Zone);
    List<ParkingSpace> getParkingSpacesByLeaseholder(ParkingUser Leaseholder);
}
