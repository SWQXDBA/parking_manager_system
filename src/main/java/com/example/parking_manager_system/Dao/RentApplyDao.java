package com.example.parking_manager_system.Dao;

import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Pojo.RentApply;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentApplyDao extends CrudRepository<RentApply,Long> {
}
