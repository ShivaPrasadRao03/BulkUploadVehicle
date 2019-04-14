/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.dao;

import com.uploadoffroad.model.Device;
import com.uploadoffroad.model.Driver;
import java.util.List;

/**
 *
 * @author mahesh
 */
public interface DriverDAO {

    public String addDriver(Driver driver);

    public String deleteDriver(Driver driver);

    public String updateDriver(Driver driver);

    public List<Driver> getAllDriver();

}
