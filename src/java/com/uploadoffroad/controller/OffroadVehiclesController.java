package com.uploadoffroad.controller;

import com.uploadoffroad.dao.OffRoadVehicleCrudDAO;
import com.uploadoffroad.model.Vehicle;
import com.uploadoffroad.util.EpochtoReadableDate;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/OffroadVehiclesController")
public class OffroadVehiclesController extends HttpServlet {

    public OffRoadVehicleCrudDAO dao;
    private static final long serialVersionUID = 1L;
    public static final String lIST_EMAIL = "jsp/fms/listvehicleinfo.jsp";
    public static final String INSERT_OR_EDIT = "jsp/fms/vehicleinformation.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        System.out.println("servlet");
        String forward = "";
        try {
            dao = new OffRoadVehicleCrudDAO();
            EpochtoReadableDate ed = new EpochtoReadableDate();
            String action = request.getParameter("action");
            System.out.println(action + "=action");
            if (action.equalsIgnoreCase("delete")) {

                Vehicle vehicle = new Vehicle();

                vehicle.setAccountID(request.getParameter("accountID"));
                vehicle.setGroupID(request.getParameter("groupID"));
                vehicle.setVehicleID(request.getParameter("vehicleID"));
                vehicle.setOfflinetime(request.getParameter("offlinetime"));

                String status = dao.deleteVehicle(vehicle);
                System.out.println(status + "=Status");
                out.println(status);
                //request.setAttribute("vehicles", dao.getAllSMS());
            } else if (action.equalsIgnoreCase("edit")) {

                long currentTimestamp = System.currentTimeMillis() / 1000;
                System.out.println("=============================");
                System.out.println(request.getParameter("editaccountID"));
                System.out.println(request.getParameter("editgroupID"));
                System.out.println(request.getParameter("editvehicleID"));
                System.out.println(request.getParameter("editphoneNumber"));
                System.out.println(request.getParameter("edituserName"));
                System.out.println(request.getParameter("editreportType"));
                System.out.println(request.getParameter("editalertStatus"));

                System.out.println(request.getParameter("editscheduleAlert"));
                System.out.println(request.getParameter("editdateOfEntry"));
                System.out.println(request.getParameter("editmessageStatus"));

                String accountID = request.getParameter("editaccountID");
                String groupID = request.getParameter("editgroupID");
                String vehicleID = request.getParameter("editvehicleID");
                String phoneNumber = request.getParameter("editphoneNumber");
                String userName = request.getParameter("edituserName");
                String reportType = request.getParameter("editreportType");
                String alertStatus = request.getParameter("editalertStatus");
                String dateOfEntry = request.getParameter("editdateOfEntry");
                String daysRangetoNotify = request.getParameter("editscheduleAlert");

                int daysRange = Integer.parseInt(daysRangetoNotify);
                System.out.println("daysRange");
                System.out.println("edit");
                Vehicle vehicle = new Vehicle();
                vehicle.setAccountID(request.getParameter("editaccountID"));
                vehicle.setGroupID(request.getParameter("editgroupID"));
                vehicle.setVehicleID(request.getParameter("editvehicleID"));

                vehicle.setOfflinetime(String.valueOf(currentTimestamp));

                long dateofentryepoch = ed.datetoEpochWithoutTime(dateOfEntry);
                System.out.println("CurrentEpoch=" + dateofentryepoch);

                String status = dao.updateVehicle(vehicle);
                out.println(status);
//                if (status.equalsIgnoreCase("Successfully updated") && (alertStatus.equalsIgnoreCase("Enable"))) {
//                    System.out.println("Alert");
//                    Vehicle alerts = new V();
//
//                    String messageStatus = alerts.SendAlert(accountID, groupID, vehicleID, userName, phoneNumber, reportType, String.valueOf(dateofentryepoch), daysRange);
//                    System.out.println("messageStatus=" + messageStatus);
//
//                }

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        long currentTimestamp = System.currentTimeMillis() / 1000;
        try {
            dao = new OffRoadVehicleCrudDAO();
            Vehicle vehicle = new Vehicle();
            EpochtoReadableDate ed = new EpochtoReadableDate();

            String accountID = request.getParameter("accountID");
            String groupID = request.getParameter("groupID");
            String vehicleID = request.getParameter("vehicleID");
            String dateOfEntry = request.getParameter("offlinetime");

            vehicle.setAccountID(accountID);
            vehicle.setGroupID(groupID);
            vehicle.setVehicleID(vehicleID);
            long dateofentryepoch = ed.datetoEpochWithoutTime(dateOfEntry);
            System.out.println("CurrentEpoch=" + dateofentryepoch);

            vehicle.setOfflinetime(String.valueOf(dateofentryepoch));

            String status = dao.addVehicle(vehicle);
//            if (status.equalsIgnoreCase("Successfully added") && (alertStatus.equalsIgnoreCase("Enable"))) {
//                System.out.println("Alert");
//                SMSAlerts alerts = new SMSAlerts();
//
//                String messageStatus = alerts.SendAlert(accountID, groupID, vehicleID, userName, phoneNumber, reportType, String.valueOf(dateofentryepoch), daysRange);
//                System.out.println("messageStatus=" + messageStatus);
//
//            }

            System.out.println("Status=" + status);
            out.println(status);

        } catch (Exception ex) {
            Logger.getLogger(OffroadVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
