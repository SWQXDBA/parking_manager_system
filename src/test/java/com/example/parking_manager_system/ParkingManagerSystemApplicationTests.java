package com.example.parking_manager_system;

import com.example.parking_manager_system.Dao.SpaceDao;
import com.example.parking_manager_system.Pojo.ParkingSpace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingManagerSystemApplicationTests {

    @Autowired
    SpaceDao spaceDao;
    @Test
    void contextLoads() {
        ParkingSpace space = new ParkingSpace();
        space.setZone("A");
        space.setIdInZone("001");
        spaceDao.save(space);

    }
    @Test
    void contextLoads2() {
        ParkingSpace space = spaceDao.findById(1L).get();
        if(space!=null){
            space.setIdInZone("002");
            spaceDao.save(space);
        }



    }
}
