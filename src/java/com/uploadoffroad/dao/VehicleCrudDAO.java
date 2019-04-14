/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.dao;

/**
 *
 * @author glodeveloper
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.uploadoffroad.dbconnection.CreateConnection;
import java.io.IOException;
import com.uploadoffroad.model.Vehicle;
import com.uploadoffroad.model.Vehicle;

public class VehicleCrudDAO implements VehicleDAO {

    private Connection dbConnection;
    private PreparedStatement pStmt;

    public VehicleCrudDAO() throws Exception {
        CreateConnection con = new CreateConnection();
        dbConnection = con.getConnection();

    }

    public String addVehicle(Vehicle vehicle) {
        String status = null;
        String insertQuery = "INSERT INTO Vehicle(accountID, deviceID,uniqueID,imeiNumber,serialNumber, simPhoneNumber, odometerOffsetKM,ignitionIndex, isActive, description,groupID,creationTime,reminderMessage)";

        System.out.println(insertQuery + "=query");
        try {
            pStmt = dbConnection.prepareStatement(insertQuery);
            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getVehicleID());
            pStmt.setString(3, vehicle.getGroupID());
//            pStmt.setString(4, vehicle.getUniqueID());
//            pStmt.setString(5, vehicle.getImeiNumber());
//            pStmt.setString(6, vehicle.getSerialNumber());
//            pStmt.setString(7, vehicle.getSimPhoneNumber());
//            pStmt.setString(8, vehicle.getOdometerOffsetKm());
//            pStmt.setInt(9, vehicle.getIgnitionIndex());
//            pStmt.setInt(10, vehicle.getIsActive());
//            pStmt.setString(11, vehicle.getDescription());
//            pStmt.setInt(12, vehicle.getCreationTime());
//            pStmt.setString(13, vehicle.getReminderMessage());

            int rs = pStmt.executeUpdate();
            System.out.println(rs + "=query");

            if (rs > 0) {
                status = "Successfully added";
                System.out.println("Success");
            } else {
                status = "Failed to add";
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            status = e.getMessage();
        }
        return status;
    }

    public String deleteVehicle(Vehicle vehicle) {
        String status = "";
        String deleteQuery = "DELETE FROM vehicleOffroad WHERE accountID = ? and vehicleID= ? and  groupID =? and offlineTime=?";
        try {
            pStmt = dbConnection.prepareStatement(deleteQuery);
            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(3, vehicle.getGroupID());
           
            int rs = pStmt.executeUpdate();
            System.out.println("Pstmt=" + pStmt);
            if (rs > 0) {
                status = "Successfully deleted";
            } else {
                status = "Failed to delete";
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public String updateVehicle(Vehicle vehicle) {
        String status = "";
        String updateQuery = "UPDATE fms_vehicle SET accountID = ?, "
                + "groupID = ?,vehicleID=?,phoneNumber = ?,userName = ?,reportType = ?,alertStatus=?,scheduleAlert=?,dateOfEntry=?,currentTimestamp=?"
                + " WHERE accountID=? AND groupID=? AND phoneNumber = ? and reportType=?";
        System.out.println("Query:" + updateQuery);
        try {
            pStmt = dbConnection.prepareStatement(updateQuery);
            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getGroupID());
          

            pStmt.setString(5, vehicle.getAccountID());
            pStmt.setString(6, vehicle.getGroupID());
          

            int rs = pStmt.executeUpdate();
            System.out.println("Pstmt=" + pStmt);
            if (rs > 0) {
                status = "Successfully updated";

            } else {
                status = "Failed to Update";
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    public List<Vehicle> getAllVehicle() {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        String query = "SELECT * FROM fms_vehicle ";
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setAccountID(rs.getString("accountID"));
                vehicle.setGroupID(rs.getString("groupID"));
                vehicle.setVehicleID(rs.getString("vehicleID"));

                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return vehicles;
    }

    public String getTotalCount() {
        System.out.println("hiii");
        String accountID = "";
        int totalcount = 0;

        String result = null;
        try {

            String sql = "SELECT accountID,count(*) from vehicleOffroad group by accountID";
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                accountID = rs.getString("accountID");
                System.out.println(accountID);
                totalcount = rs.getInt(2);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountID + "#" + totalcount;
    }

    public static void main(String[] args) {
        try {
            OffRoadVehicleCrudDAO of = new OffRoadVehicleCrudDAO();

            String result = of.getTotalCount();
            System.out.println("Result =" + result);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
