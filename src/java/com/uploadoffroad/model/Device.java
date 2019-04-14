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
public class Device {

    private String accountID;

    private String deviceID;

    private String groupID;

    private String uniqueID;

    private String imeiNumber;

    private String serialNumber;

    private String simPhoneNumber;

    private String odometerOffsetKm;

    private int ignitionIndex;

    private int isActive;

    private String description;

    private int creationTime;

    private String reminderMessage;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    public String getImeiNumber() {
        return imeiNumber;
    }

    public void setImeiNumber(String imeiNumber) {
        this.imeiNumber = imeiNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSimPhoneNumber() {
        return simPhoneNumber;
    }

    public void setSimPhoneNumber(String simPhoneNumber) {
        this.simPhoneNumber = simPhoneNumber;
    }

    public String getOdometerOffsetKm() {
        return odometerOffsetKm;
    }

    public void setOdometerOffsetKm(String odometerOffsetKm) {
        this.odometerOffsetKm = odometerOffsetKm;
    }

    public int getIgnitionIndex() {
        return ignitionIndex;
    }

    public void setIgnitionIndex(int ignitionIndex) {
        this.ignitionIndex = ignitionIndex;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

    public String getReminderMessage() {
        return reminderMessage;
    }

    public void setReminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
    }


}
