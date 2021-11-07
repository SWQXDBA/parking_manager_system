package com.example.parking_manager_system.Dao;

import com.example.parking_manager_system.Pojo.AdminUser;
import com.example.parking_manager_system.Pojo.OptionLog;
import org.springframework.data.repository.CrudRepository;

public interface OptionLogDao  extends CrudRepository<OptionLog,Long> {
}
