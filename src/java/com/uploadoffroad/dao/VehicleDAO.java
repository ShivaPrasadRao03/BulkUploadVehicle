/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.dao;

import com.uploadoffroad.model.Vehicle;
import java.util.List;

/**
 *
 * @author glodeveloper
 */
public interface VehicleDAO {
     public String addVehicle(Vehicle vehicle);

    public String deleteVehicle(Vehicle vehicle);

    public String updateVehicle(Vehicle vehicle);

    public List<Vehicle> getAllVehicle();
}
