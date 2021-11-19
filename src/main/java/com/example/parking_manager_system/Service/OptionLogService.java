package com.example.parking_manager_system.Service;

import com.example.parking_manager_system.Dao.OptionLogDao;
import com.example.parking_manager_system.Pojo.OptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class OptionLogService {
    @Autowired
    OptionLogDao optionLogDao;

    public List<OptionLog> getAll(){

        return new ArrayList<>((Collection<? extends OptionLog>) optionLogDao.findAll());
    }

    public void save(OptionLog log){
        optionLogDao.save(log);
    }
}
