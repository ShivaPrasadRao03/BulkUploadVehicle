/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.model;

/**
 *
 * @author mahesh
 */
public class DeviceList {

    private String accountID;

    private String groupID;

    private String deviceID;

    private int lastUpdationTime;

    private int creationTime;

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

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public int getLastUpdationTime() {
        return lastUpdationTime;
    }

    public void setLastUpdationTime(int lastUpdationTime) {
        this.lastUpdationTime = lastUpdationTime;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

   

}
