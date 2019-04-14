/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.dao;

import com.uploadoffroad.model.Device;
import java.util.List;

/**
 *
 * @author glodeveloper
 */
public interface DeviceDAO {
     public String addDevice(Device device);

    public String deleteDevice(Device device);

    public String updateDevice(Device device);

    public List<Device> getAllDevice();
}
