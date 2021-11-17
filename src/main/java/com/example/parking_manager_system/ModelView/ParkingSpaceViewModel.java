package com.example.parking_manager_system.ModelView;

import java.sql.Timestamp;

/**
 * @author SWQXDBA
 */
public class ParkingSpaceViewModel {

   public Long id;

    public  String zone;

    public String idInZone;

    public Timestamp startLeaseTime;

    public  Timestamp expirationTime;

    public ParkingUserViewModel leaseholder;



}
