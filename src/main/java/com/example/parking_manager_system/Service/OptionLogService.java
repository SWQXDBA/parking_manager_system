package com.example.parking_manager_system.Service;

import com.example.parking_manager_system.Dao.OptionLogDao;
import com.example.parking_manager_system.Pojo.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionLogService {
    @Autowired
    OptionLogDao optionLogDao;

    public void save(OptionLog log){
        optionLogDao.save(log);
    }
}
