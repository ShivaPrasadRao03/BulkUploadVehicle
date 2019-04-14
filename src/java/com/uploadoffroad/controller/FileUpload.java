/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uploadoffroad.controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.uploadoffroad.dao.DeviceCrudDAO;
import com.uploadoffroad.dao.DeviceListCrudDAO;
import com.uploadoffroad.dao.OffRoadVehicleCrudDAO;
import com.uploadoffroad.dbconnection.CreateConnection;
import com.uploadoffroad.model.Device;
import com.uploadoffroad.model.DeviceList;
import com.uploadoffroad.model.Vehicle;
import com.uploadoffroad.util.EpochtoReadableDate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;

/**
 *
 * @author glodeveloper
 */
@MultipartConfig
@WebServlet(name = "FileUpload", urlPatterns = {"/FileUpload"})
public class FileUpload extends HttpServlet {

    private DeviceCrudDAO dao;
    private DeviceListCrudDAO devicelistdao;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FileUpload</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FileUpload at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = "";
        System.out.println("post file uploads");
        PrintWriter out = response.getWriter();

        String accountID = request.getParameter("accountID");
        System.out.println("accountID=" + accountID);

        String groupID = request.getParameter("groupID");
        System.out.println("GroupID=" + groupID);

        String trackerType = request.getParameter("trackerType");
        System.out.println("trackerType=" + trackerType);

        String ignitionIndex = request.getParameter("ignitionIndex");
        System.out.println("ignitionIndex=" + ignitionIndex);

        String baseLocation = request.getParameter("baseLocation");
        System.out.println("BaseLocation=" + baseLocation);

        String technician = request.getParameter("userID");
        System.out.println("Technician=" + technician);
       

        String isActive = request.getParameter("isActive");
        System.out.println("IsActive=" + isActive);

        EpochtoReadableDate ed = new EpochtoReadableDate();
        System.out.println("dfdfdpost");
        try {
//            System.out.println("bvniofhb");
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/User", "root", "gl0v1s10n");
//            con.setAutoCommit(false);
//            PreparedStatement pstm = null;

            CreateConnection con = new CreateConnection();

            PreparedStatement pstm = null;

            Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
            InputStream filecontent = filePart.getInputStream();
            System.out.println("Filecontent=" + filecontent);

            // FileInputStream input = new FileInputStream("/home/glodeveloper/Desktop" + File.separator + name);
            POIFSFileSystem fs = new POIFSFileSystem(filecontent);
            System.out.println("filepath" + fs);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            System.out.println("filepath" + wb);
            HSSFSheet sheet = wb.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            Row row;
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                row = sheet.getRow(i);

                //accountID = row.getCell(0).getStringCellValue();
                //String accountID= "gvk-up-108";
                System.out.println("AccountID=" + accountID);

                String deviceID = row.getCell(0).getStringCellValue();
                System.out.println("DeviceID=" + deviceID);

                String uniqueID = NumberToTextConverter.toText(row.getCell(1).getNumericCellValue());
                uniqueID = trackerType + "_" + uniqueID;
                System.out.println("uniquerID=" + uniqueID);

                String imeiNumber = NumberToTextConverter.toText(row.getCell(2).getNumericCellValue());

                String simNumber = NumberToTextConverter.toText(row.getCell(3).getNumericCellValue());

                String vehicleDescription = row.getCell(4).getStringCellValue();
                System.out.println("VehicleDescription="+vehicleDescription);
                String odometerReading = NumberToTextConverter.toText(row.getCell(5).getNumericCellValue());
System.out.println("odometerReading="+odometerReading);
                long creationTime = ed.currentEpoch();

                /* add device table */
                dao = new DeviceCrudDAO();

                Device device = new Device();

                device.setAccountID(accountID);
                device.setDeviceID(deviceID);
                device.setDescription("[" + deviceID + "]" + "[" + vehicleDescription + "]" + "[" + groupID + "]");
                device.setUniqueID(uniqueID);
                device.setImeiNumber(imeiNumber);
                device.setSerialNumber(imeiNumber);
                device.setSimPhoneNumber(simNumber);
                device.setIgnitionIndex(Integer.parseInt(ignitionIndex));
                device.setOdometerOffsetKm(odometerReading);
                device.setCreationTime((int) creationTime);
                device.setReminderMessage(technician);

                status = dao.addDevice(device);
                System.out.println(status);

                /* add deviceList table */
                devicelistdao = new DeviceListCrudDAO();

                DeviceList devicelist = new DeviceList();
                devicelist.setAccountID(accountID);
                devicelist.setGroupID(groupID);
                devicelist.setDeviceID(deviceID);
                devicelist.setCreationTime((int) creationTime);
                status = devicelistdao.addDeviceList(devicelist);
                System.out.println(status);

            }
            out.print(status);

            System.out.println("Success import excel to mysql table");

        } catch (Exception e) {
            e.printStackTrace();
        }
        // con.commit();
        // pstm.close();
        // con.connectionClose();

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public void stringcheck(String value) {
        String accountID = value.trim();

    }
}
