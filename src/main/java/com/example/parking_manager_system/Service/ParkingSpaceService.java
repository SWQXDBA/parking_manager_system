package com.example.parking_manager_system.Service;

import com.example.parking_manager_system.Dao.ParkingSpaceDao;
import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Pojo.ParkingUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ParkingSpaceService {
    @Autowired
    ParkingSpaceDao spaceDao;

    /**
     * 出租停车场 如果想要出租的停车场已经有所有者 所有者不是新的承租人 并且租期还没结束 则返回false
     * @param user
     * @param space
     * @param from
     * @param to
     * @return
     */
    public boolean rentOut(ParkingUser user, ParkingSpace space, Timestamp from, Timestamp to){
        ParkingUser oldOwner = space.getLeaseholder();
        //已经有承租人
        if(oldOwner!=null){
            //旧的承租人不等于新的承租人
            if(!oldOwner.getId().equals(user.getId())){
                //租期还未结束
                if(space.getExpirationTime().getTime()>=to.getTime()){
                    return false;
                }
            }
        }
        space.setStartLeaseTime(from);
        space.setExpirationTime(to);
        space.setLeaseholder(user);
        spaceDao.save(space);
        return true;
    }

    /**
     * 根据停车场分区 获取该区域的所有停车场
     * @param zoneName
     * @return
     */
    public List<ParkingSpace> getSpacesByZone(String zoneName){
        return spaceDao.getParkingSpacesByZone(zoneName);
    }

    public List<ParkingSpace>  getSpaceByUser(ParkingUser user){
        return spaceDao.getParkingSpacesByLeaseholder(user);
    }
}
