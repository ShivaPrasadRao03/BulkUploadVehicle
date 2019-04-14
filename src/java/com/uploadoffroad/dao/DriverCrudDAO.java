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
import com.uploadoffroad.model.Driver;
import com.uploadoffroad.model.Driver;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverCrudDAO implements DriverDAO {

    private Connection dbConnection;
    private PreparedStatement pStmt;

    public DriverCrudDAO() throws Exception {
        CreateConnection con = new CreateConnection();
        dbConnection = con.getConnection();

    }

    public String addDriver(Driver vehicle) {
        String status = null;
        //String insertQuery = "INSERT INTO Driver(accountID, driverID,uniqueID,imeiNumber,serialNumber, simPhoneNumber, odometerOffsetKM,ignitionIndex, isActive, description,groupID,creationTime,reminderMessage)";
        String insertQuery = "INSERT INTO Driver(accountID,driverID,deviceID,contactPhone,description,address) VALUES(?,?,?,?,?,?)";

        System.out.println(insertQuery + "=query");
        try {
            pStmt = dbConnection.prepareStatement(insertQuery);

            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getDriverID());

            pStmt.setString(3, vehicle.getDeviceID());
            pStmt.setString(4, vehicle.getContactPhone());

            pStmt.setString(5, vehicle.getDescription());

            pStmt.setString(6, vehicle.getAddress());

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
                Logger.getLogger(DriverCrudDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return status;
    }

    public String deleteDriver(Driver vehicle) {
        String status = "";
        String deleteQuery = "DELETE FROM Driver WHERE accountID = ? and driverID = ? and  deviceID =?";
        try {
            pStmt = dbConnection.prepareStatement(deleteQuery);
            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getDriverID());
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

    public String updateDriver(Driver vehicle) {
        String status = "";
        String updateQuery = "UPDATE Driver set accountID=?,driverID=?,deviceID=?,contactPhone=?,description=?,address=? where accountID=? AND driverID=?";
        System.out.println("Query:" + updateQuery);
        try {
            pStmt = dbConnection.prepareStatement(updateQuery);
            pStmt.setString(1, vehicle.getAccountID());
            pStmt.setString(2, vehicle.getDriverID());

            pStmt.setString(3, vehicle.getDeviceID());
            pStmt.setString(4, vehicle.getContactPhone());
            pStmt.setString(5, vehicle.getDescription());
            pStmt.setString(6, vehicle.getAddress());
            pStmt.setString(7, vehicle.getAccountID());
            pStmt.setString(8, vehicle.getDriverID());
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

    public List<Driver> getAllDriver() {
        List<Driver> vehicles = new ArrayList<Driver>();

        String query = "SELECT * FROM Driver ";
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Driver vehicle = new Driver();
                vehicle.setAccountID(rs.getString("accountID"));
               
                vehicle.setDriverID(rs.getString("driverID"));

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
            DriverCrudDAO of = new DriverCrudDAO();

            String result = of.getTotalCount();
            System.out.println("Result =" + result);
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
