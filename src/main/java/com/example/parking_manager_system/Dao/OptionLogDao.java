package com.example.parking_manager_system.Dao;

import com.example.parking_manager_system.Pojo.AdminUser;
import com.example.parking_manager_system.Pojo.OptionLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionLogDao  extends CrudRepository<OptionLog,Long> {
}
