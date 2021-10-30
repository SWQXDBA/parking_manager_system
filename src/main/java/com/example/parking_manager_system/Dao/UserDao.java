package com.example.parking_manager_system.Dao;

import com.example.parking_manager_system.Pojo.ParkingUser;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<ParkingUser,Long> {
    public ParkingUser getParkingUserByUserNameAndPassWord(String UserName,String PassWord);
    public ParkingUser getParkingUserByUserName(String UserName);
}
