/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.dao;

import java.util.List;
import com.uploadoffroad.model.Vehicle;

/**
 *
 * @author Glodeveloper
 */
public interface OffRoadVehicleDAO {
    
    public String addVehicle(Vehicle vehicle);

    public String deleteVehicle(Vehicle vehicle);

    public String updateVehicle(Vehicle vehicle);

    public List<Vehicle> getAllVehicle();

    
}