package com.example.parking_manager_system.Dao;

import com.example.parking_manager_system.Pojo.AdminUser;
import com.example.parking_manager_system.Pojo.ParkingSpace;
import org.springframework.data.repository.CrudRepository;

public interface AdminUserDao extends CrudRepository<AdminUser,Long> {
    AdminUser getAdminUserByUserNameAndPassword(String UserName,String Password);
}
