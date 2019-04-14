/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.util;

import com.uploadoffroad.dbconnection.CreateConnection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author mahesh
 */
public class GetData {

    public static String getAccounts() {
        String accountID = "";
        JSONArray jArray = new JSONArray();
        try {
            CreateConnection con = new CreateConnection();
            Statement stmt = con.getStatement();
            String sql = "Select distinct accountID FROM Account";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                accountID = rs.getString("accountID");
                
                JSONObject jObj = new JSONObject();
                jObj.put("accountID", accountID);
                
                
                
                jArray.put(jObj);
            }
            JSONObject jObjDevice = new JSONObject();
            jObjDevice.put("account", jArray);
//            JSONObject jObjDeviceList = new JSONObject();
//            jObjDeviceList.put("devicelist", jObjDevice);
            
            accountID = jObjDevice.toString();

        } catch (Exception e) {
            e.printStackTrace();;
        }
        return accountID;
    }
    
     public static String getGroups(String accountID) {
        String groupID = "";
        JSONArray jArray = new JSONArray();
        try {
            CreateConnection con = new CreateConnection();
            Statement stmt = con.getStatement();
            String sql = "Select distinct groupID FROM DeviceGroup where accountID='"+accountID+"'";
            System.out.println("sql="+sql);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                groupID = rs.getString("groupID");
                
                JSONObject jObj = new JSONObject();
                jObj.put("groupID", groupID);
                
                
                
                jArray.put(jObj);
            }
            JSONObject jObjDevice = new JSONObject();
            jObjDevice.put("group", jArray);
//            JSONObject jObjDeviceList = new JSONObject();
//            jObjDeviceList.put("devicelist", jObjDevice);
            
            groupID = jObjDevice.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupID;
    }

    public static void main(String[] args) {
        
        String accountID = getAccounts();
        System.out.println("accountID="+accountID);
        String groupID = getGroups("als");
        System.out.println("groupID="+groupID);
    }

}
