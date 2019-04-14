/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.dao;

import com.uploadoffroad.model.Device;
import com.uploadoffroad.model.DeviceList;
import java.util.List;

/**
 *
 * @author mahesh
 */
public interface DeviceListDAO {

    public String addDeviceList(DeviceList devicelist);

    public String deleteDeviceList(DeviceList devicelist);

    public String updateDeviceList(DeviceList devicelist);

    public List<DeviceList> getAllDeviceList();

}
