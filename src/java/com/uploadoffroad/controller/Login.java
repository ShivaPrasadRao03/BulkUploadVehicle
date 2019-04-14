package com.uploadoffroad.controller;

import com.uploadoffroad.dao.UserDAO;
import com.uploadoffroad.dbconnection.CreateConnection;
import com.uploadoffroad.util.EpochtoReadableDate;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("production/dashboard.jsp");
        rd.include(req, resp);
    } */
    static Logger log = Logger.getLogger(Login.class.getName());
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Login.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        System.out.println("helloddddd");
        EpochtoReadableDate ed = new EpochtoReadableDate();

        long epoch = System.currentTimeMillis() / 1000;
        //long to string
        String currenttimestamp = ed.convertTime(Long.valueOf(epoch));

        logger.warn("==============Login===============");
        logger.warn("Login Time:" + currenttimestamp);

        String accountID = request.getParameter("accountID");
        System.out.println("LoginaccountID=" + accountID);
        logger.warn("Login AccountID=" + accountID);

        String username = request.getParameter("username");
        System.out.println(username + "=UserName");
        logger.warn("Login Username=" + username);

        String password = request.getParameter("password");
        System.out.println(password + "=Password");
        logger.warn("Login Password=" + password);

        UserDAO ud = new UserDAO();
        String role = "";

        HttpSession hs = request.getSession();
        if (ud.validate(accountID, username, password)) {
            
            
            

//            CreateConnection con = null;
//            try {
//                con = new CreateConnection();
////
////                Statement st1 = con.getStatement();
////                ResultSet rs = st1.executeQuery("select * from registration where accountID=\'" + accountID + "\' and  userID=\'" + username + "\'  and password=\'" + password + "\'");
////                while (rs.next()) {
////                    role = rs.getString("roleID");
////                }
//
//            } catch (Exception e) {
//                e.getMessage();
//            }

            //start a session
            hs.setAttribute("account", accountID);
            hs.setAttribute("uname", username);
            hs.setAttribute("role", role);
            hs.setAttribute("Password", password);

            out.println("Valid");

            logger.warn("success");
        } else {

            logger.warn("failed");
            if (ud.validate(accountID, username, password) == false) {
                System.out.println("Incorrect");
                out.println("incorrect");
            }
            /*String msg = "Incorrect Login details";
            hs.setAttribute("msg", msg);

            RequestDispatcher rd = request.getRequestDispatcher("jsp/login.jsp");
            rd.include(request, response);
            return; */
        }

    }

}
