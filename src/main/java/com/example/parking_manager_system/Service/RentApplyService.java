package com.example.parking_manager_system.Service;

import com.example.parking_manager_system.Dao.RentApplyDao;
import com.example.parking_manager_system.Pojo.RentApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RentApplyService {
    @Autowired
    RentApplyDao rentApplyDao;
    public void applyRent(RentApply apply){
        rentApplyDao.save(apply);
    }
    public RentApply getRentById(long id){
        Optional<RentApply> optional = rentApplyDao.findById(id);
        if(optional.isEmpty()){
            return null;
        }
        return optional.get();
    }
    public void deleteRentApply(RentApply rentApply){
        rentApplyDao.delete(rentApply);
    }
}
