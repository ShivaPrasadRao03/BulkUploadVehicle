package com.uploadoffroad.controller;

import com.uploadoffroad.util.EpochtoReadableDate;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.logging.Logger;
@WebServlet("/logout")
public class Logout extends HttpServlet {
    static Logger log = Logger.getLogger(Logout.class.getName());
    final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Logout.class);


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        System.out.println("logoutservlet");
        request.getRequestDispatcher("jsp/login.jsp").include(request, response);

        HttpSession session = request.getSession();
        session.invalidate();
        System.out.println("Successfully logged out");
       
        //Log out time
        EpochtoReadableDate ed = new EpochtoReadableDate();

        long epoch = System.currentTimeMillis() / 1000;
        //long to string
        String currenttimestamp = ed.convertTime(Long.valueOf(epoch));

         logger.warn("Logout Time:" + currenttimestamp);
        logger.warn("==============Logout===============");
       
        
        //request.setAttribute("msg","You are successfully logged out!");
       
        
        out.close();
    }
}
