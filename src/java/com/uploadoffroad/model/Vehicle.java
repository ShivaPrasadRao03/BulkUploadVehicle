/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.model;

/**
 *
 * @author glodeveloper
 */
public class Vehicle {
    
    private String accountID;
    
    private String groupID;
    
    private String vehicleID;
    
    private String offlinetime;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getOfflinetime() {
        return offlinetime;
    }

    public void setOfflinetime(String offlinetime) {
        this.offlinetime = offlinetime;
    }
    
    
}
