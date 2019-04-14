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
import com.uploadoffroad.model.Device;
import java.io.IOException;
import com.uploadoffroad.model.DeviceList;
import com.uploadoffroad.model.DeviceList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeviceListCrudDAO implements DeviceListDAO {

    private Connection dbConnection;
    private PreparedStatement pStmt;

    public DeviceListCrudDAO() throws Exception {
        CreateConnection con = new CreateConnection();
        dbConnection = con.getConnection();

    }

    public String addDeviceList(DeviceList vehicle) {
        String status = null;
        //String insertQuery = "INSERT INTO DeviceList(accountID, deviceID,uniqueID,imeiNumber,serialNumber, simPhoneNumber, odometerOffsetKM,ignitionIndex, isActive, description,groupID,creationTime,reminderMessage)";
        String insertQuery = "INSERT INTO DeviceList(accountID,groupID,deviceID,lastUpdateTime,creationTime) VALUES(?,?,?,?,?)";

        System.out.println(insertQuery + "=query");
        try {
            pStmt = dbConnection.prepareStatement(insertQuery);

            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getGroupID());

            pStmt.setString(3, vehicle.getDeviceID());
            pStmt.setInt(4, vehicle.getLastUpdationTime());

            pStmt.setInt(5, vehicle.getCreationTime());

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
                Logger.getLogger(DeviceListCrudDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    public String deleteDeviceList(DeviceList vehicle) {
        String status = "";
        String deleteQuery = "DELETE FROM DeviceList WHERE accountID = ? and  groupID =? and deviceID=?";
        try {
            pStmt = dbConnection.prepareStatement(deleteQuery);
            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getGroupID());
            pStmt.setString(3, vehicle.getDeviceID());

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

    public String updateDeviceList(DeviceList vehicle) {
        String status = "";
        String updateQuery = "UPDATE DeviceList SET accountID = ?, "
                + "groupID = ?,vehicleID=?,lastUpdateTime=?,creationTime=?"
                + " WHERE accountID=? AND groupID=? AND vehicleID = ?";
        System.out.println("Query:" + updateQuery);
        try {
            pStmt = dbConnection.prepareStatement(updateQuery);
            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getGroupID());

            pStmt.setString(3, vehicle.getDeviceID());
            pStmt.setInt(4, vehicle.getLastUpdationTime());

            pStmt.setInt(5, vehicle.getCreationTime());
            pStmt.setString(6, vehicle.getAccountID());
            pStmt.setString(7, vehicle.getGroupID());

            pStmt.setString(8, vehicle.getDeviceID());

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

    public List<DeviceList> getAllDeviceList() {
        List<DeviceList> vehicles = new ArrayList<DeviceList>();

        String query = "SELECT * FROM fms_vehicle ";
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                DeviceList vehicle = new DeviceList();
                vehicle.setAccountID(rs.getString("accountID"));
                vehicle.setGroupID(rs.getString("groupID"));

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
            DeviceListCrudDAO of = new DeviceListCrudDAO();

            String result = of.getTotalCount();
            System.out.println("Result =" + result);
        } catch (Exception e) {
            e.getMessage();
        }
    }

   

}
