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
import com.uploadoffroad.model.Vehicle;

public class OffRoadVehicleCrudDAO implements OffRoadVehicleDAO {

    private Connection dbConnection;
    private PreparedStatement pStmt;

    public OffRoadVehicleCrudDAO() throws Exception {
        CreateConnection con = new CreateConnection();
        dbConnection = con.getConnection();

    }

    public String addVehicle(Device offroadvehicle) {
        String status = null;
        String insertQuery = "INSERT INTO Device(accountID, deviceID,uniqueID,imeiNumber,serialNumber, simPhoneNumber, odometerOffsetKM,ignitionIndex, isActive, description,groupID,creationTime,reminderMessage)";

        System.out.println(insertQuery + "=query");
        try {
            pStmt = dbConnection.prepareStatement(insertQuery);
            pStmt.setString(1, offroadvehicle.getAccountID());
            pStmt.setString(2, offroadvehicle.getDeviceID());
            pStmt.setString(3, offroadvehicle.getGroupID());
            pStmt.setString(4, offroadvehicle.getUniqueID());
            pStmt.setString(5, offroadvehicle.getImeiNumber());
            pStmt.setString(6, offroadvehicle.getSerialNumber());
            pStmt.setString(7, offroadvehicle.getSimPhoneNumber());
            pStmt.setString(8, offroadvehicle.getOdometerOffsetKm());
            pStmt.setInt(9, offroadvehicle.getIgnitionIndex());
            pStmt.setInt(10, offroadvehicle.getIsActive());
            pStmt.setString(11, offroadvehicle.getDescription());
            pStmt.setInt(12, offroadvehicle.getCreationTime());
            pStmt.setString(13, offroadvehicle.getReminderMessage());

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

    public String deleteVehicle(Device offroadvehicle) {
        String status = "";
        String deleteQuery = "DELETE FROM vehicleOffroad WHERE accountID = ? and vehicleID= ? and  groupID =? and offlineTime=?";
        try {
            pStmt = dbConnection.prepareStatement(deleteQuery);
            pStmt.setString(1, offroadvehicle.getAccountID());
            pStmt.setString(3, offroadvehicle.getGroupID());
           
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

    public String updateVehicle(Device offroadvehicle) {
        String status = "";
        String updateQuery = "UPDATE fms_offroadvehicle SET accountID = ?, "
                + "groupID = ?,vehicleID=?,phoneNumber = ?,userName = ?,reportType = ?,alertStatus=?,scheduleAlert=?,dateOfEntry=?,currentTimestamp=?"
                + " WHERE accountID=? AND groupID=? AND phoneNumber = ? and reportType=?";
        System.out.println("Query:" + updateQuery);
        try {
            pStmt = dbConnection.prepareStatement(updateQuery);
            pStmt.setString(1, offroadvehicle.getAccountID());
            pStmt.setString(2, offroadvehicle.getGroupID());
          

            pStmt.setString(5, offroadvehicle.getAccountID());
            pStmt.setString(6, offroadvehicle.getGroupID());
          

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
        List<Vehicle> offroadvehicles = new ArrayList<Vehicle>();

        String query = "SELECT * FROM fms_offroadvehicle ";
        try {
            Statement stmt = dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Vehicle offroadvehicle = new Vehicle();
                offroadvehicle.setAccountID(rs.getString("accountID"));
                offroadvehicle.setGroupID(rs.getString("groupID"));
                offroadvehicle.setVehicleID(rs.getString("vehicleID"));

                offroadvehicles.add(offroadvehicle);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return offroadvehicles;
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

    @Override
    public String addVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
