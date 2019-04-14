/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.dao;

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
import com.uploadoffroad.model.Device;
import com.uploadoffroad.model.Device;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeviceCrudDAO implements DeviceDAO {

    private Connection dbConnection;
    private PreparedStatement pStmt;

    public DeviceCrudDAO() throws Exception {
        CreateConnection con = new CreateConnection();
        dbConnection = con.getConnection();

    }

    public String addDevice(Device vehicle) {
        String status = null;
        //String insertQuery = "INSERT INTO Device(accountID, deviceID,uniqueID,imeiNumber,serialNumber, simPhoneNumber, odometerOffsetKM,ignitionIndex, isActive, description,groupID,creationTime,reminderMessage)";
        String insertQuery = "INSERT INTO Device(accountID, deviceID,uniqueID,"
                + "imeiNumber,serialNumber, simPhoneNumber,"
                + "creationTime,description,reminderMessage,ignitionIndex) VALUES(?,?,?,?,?,?,?,?,?,?)";

        System.out.println(insertQuery + "=query");
        try {
            pStmt = dbConnection.prepareStatement(insertQuery);
//            pStmt.setString(1, vehicle.getAccountID());
//            pStmt.setString(2, vehicle.getDeviceID());
//            pStmt.setString(3, vehicle.getGroupID());
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

            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getDeviceID());

            pStmt.setString(3, vehicle.getUniqueID());
            pStmt.setString(4, vehicle.getImeiNumber());
            pStmt.setString(5, vehicle.getSerialNumber());

            pStmt.setString(6, vehicle.getSimPhoneNumber());

            pStmt.setInt(7, vehicle.getCreationTime());
            pStmt.setString(8, vehicle.getDescription());
            pStmt.setString(9, vehicle.getReminderMessage());
            pStmt.setInt(10, vehicle.getIgnitionIndex());

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
        } finally {
            try {
                dbConnection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DeviceCrudDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    public String deleteDevice(Device vehicle) {
        String status = "";
        String deleteQuery = "DELETE FROM Device WHERE accountID = ? and vehicleID= ? and  groupID =? and offlineTime=?";
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

    public String updateDevice(Device vehicle) {
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

    public List<Device> getAllDevice() {
        List<Device> vehicles = new ArrayList<Device>();

        String query = "SELECT * FROM fms_vehicle ";
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Device vehicle = new Device();
                vehicle.setAccountID(rs.getString("accountID"));
                vehicle.setGroupID(rs.getString("groupID"));
                vehicle.setDeviceID(rs.getString("vehicleID"));

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
            DeviceCrudDAO of = new DeviceCrudDAO();

            String result = of.getTotalCount();
            System.out.println("Result =" + result);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
