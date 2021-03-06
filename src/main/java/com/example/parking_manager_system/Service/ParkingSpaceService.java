package com.example.parking_manager_system.Service;

import com.example.parking_manager_system.Dao.ParkingSpaceDao;
import com.example.parking_manager_system.Dao.UserDao;
import com.example.parking_manager_system.ModelView.ParkingSpaceUpdateViewModel;
import com.example.parking_manager_system.Pojo.ParkingSpace;
import com.example.parking_manager_system.Pojo.ParkingUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceService {
    @Autowired
    ParkingSpaceDao spaceDao;
    @Autowired
    UserDao userDao;
    public boolean update(ParkingSpaceUpdateViewModel viewModel){

        ParkingSpace space = spaceDao.getParkingSpacesByIdInZoneAndZone(viewModel.idInZone, viewModel.zone);
        if(viewModel.startRentTime!=null){
            space.setStartLeaseTime(viewModel.startRentTime);
        }
        if(viewModel.endRentTime!=null){
            space.setExpirationTime (viewModel.endRentTime);
        }
        if(viewModel.userName!=null){
            ParkingUser user = userDao.getParkingUserByUserName(viewModel.userName);
            if(user==null){
                return false;
            }
            space.setLeaseholder(user);
        }
        if(viewModel.parkingState!=null){
            String state = viewModel.parkingState;
            ParkingSpace.ParkingState stateEm = ParkingSpace.ParkingState.FREE;
            switch (state) {
                case "RENTED":
                    stateEm = ParkingSpace.ParkingState.RENTED;
                    break;
                case "FREE":
                    stateEm = ParkingSpace.ParkingState.FREE;
                    break;
                case "OCCUPY":
                    stateEm = ParkingSpace.ParkingState.OCCUPY;
                    break;
            }

            space.setParkingState (stateEm);
        }
        spaceDao.save(space);
        return true;

    }
    /**
     * ???????????????
     *
     * @param user
     * @param space
     * @param from
     * @param to
     * @return
     */
    public void rentOut(ParkingUser user, ParkingSpace space, Timestamp from, Timestamp to) {
  /*         ParkingUser oldOwner = space.getLeaseholder();
     //??????????????????
        if(oldOwner!=null){
            //???????????????????????????????????????
            if(!oldOwner.getId().equals(user.getId())){
                //??????????????????
                if(space.getExpirationTime().getTime()>=to.getTime()){
                    return false;
                }
            }
        }*/
        space.setParkingState(ParkingSpace.ParkingState.RENTED);
        space.setStartLeaseTime(from);
        space.setExpirationTime(to);
        space.setLeaseholder(user);
        spaceDao.save(space);
    }

    public ParkingSpace getSpaceById(long id) {
        Optional<ParkingSpace> optional = spaceDao.findById(id);
        return optional.orElse(null);
    }
    public ParkingSpace getSpaceByInZoneAndZone(String idInZone,String zone) {

        return spaceDao.getParkingSpacesByIdInZoneAndZone(idInZone,zone);
    }
    /**
     * ????????????????????? ?????????????????????????????????
     *
     * @param zoneName
     * @return
     */
    public List<ParkingSpace> getSpacesByZone(String zoneName) {
        return spaceDao.getParkingSpacesByZone(zoneName);
    }


    public List<ParkingSpace> getAllSpacesSaveData() {
        return new ArrayList<>((Collection<? extends ParkingSpace>) spaceDao.findAll());
    }

    public List<ParkingSpace> getSpaceByUser(ParkingUser user) {
        return spaceDao.getParkingSpacesByLeaseholder(user);
    }
}
