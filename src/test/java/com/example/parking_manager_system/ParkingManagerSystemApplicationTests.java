package com.example.parking_manager_system;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingManagerSystemApplicationTests {

/*    @Autowired
    ParkingSpaceDao spaceDao;
    @Autowired
    AdminUserService adminUserService;
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
    @Test
    void adm() {
        //adminUserService.register("root","root");
    }
    @Test
    void addp() {
        List<ParkingSpace> list = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            ParkingSpace space = new ParkingSpace();
            space.setZone("A");
            space.setIdInZone(""+i);
            list.add(space);
        }
        for (int i = 1; i <= 50; i++) {
            ParkingSpace space = new ParkingSpace();
            space.setZone("B");
            space.setIdInZone(""+i);
            list.add(space);
        }
        for (int i = 1; i <= 25; i++) {
            ParkingSpace space = new ParkingSpace();
            space.setZone("C");
            space.setIdInZone(""+i);
            list.add(space);
        }
        spaceDao.saveAll(list);
    }*/
}
