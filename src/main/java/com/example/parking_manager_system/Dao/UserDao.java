package com.example.parking_manager_system.Dao;

import com.example.parking_manager_system.Pojo.ParkingUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<ParkingUser,Long> {
    public ParkingUser getParkingUserByUserNameAndPassWord(String UserName,String PassWord);

    /**
     * 获取一个用户的所有停车场 包括已经到期的
     * @param UserName
     * @return
     */
    public ParkingUser getParkingUserByUserName(String UserName);
}
