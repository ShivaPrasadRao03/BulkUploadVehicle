///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.uploadoffroad.controller;
//
//
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.sql.PreparedStatement;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Random;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.RequestDispatcher;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.servlet.http.Part;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.util.NumberToTextConverter;
//
///**
// *
// * @author glodeveloper
// */
//@WebServlet(urlPatterns = {"/InwardServlet"})
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
//        maxFileSize = 1024 * 1024 * 50, // 50 MB
//        maxRequestSize = 1024 * 1024 * 100)       // 100 MB
//public class InwardServlet extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    private static final long serialVersionUID = 205242440643911308L;
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    private static final String UPLOAD_DIR = "home/glodeveloper/Desktop/jar_files";
//
//    private String getFileName(final Part part) {
//        final String partHeader = part.getHeader("content-disposition");
//
//        for (String content : part.getHeader("content-disposition").split(";")) {
//            if (content.trim().startsWith("filename")) {
//                return content.substring(
//                        content.indexOf('=') + 1).trim().replace("\"", "");
//            }
//        }
//        return null;
//    }
////     private String getPhotoName(Part part1) {
////        String contentDisp = part1.getHeader("content-disposition");
////        System.out.println("content-disposition header= "+contentDisp);
////        String[] tokens = contentDisp.split(";");
////        for (String token : tokens) {
////            if (token.trim().startsWith("photo")) {
////                return token.substring(token.indexOf("=") + 2, token.length()-1);
////            }
////        }
////        return "";
////    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//      
//        PreparedStatement pstm = null;
//        int rs = 0;
//        HttpSession hs = request.getSession();
//        try {
//            String Invoice = request.getParameter("invoice");
//            System.out.println("invoice" + Invoice);
//            String Suppliersname = request.getParameter("supname");
//            String Quantity = request.getParameter("quantity");
//            String Purchase1 = request.getParameter("date");
//            DateFormat dF = new SimpleDateFormat("dd/MM/yyyy"); // The mask
//
//            // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
//            Date date1 = dF.parse(Purchase1); // parsing the String into a Date using the mask
//            //Date.getTime() method gives you the Long with milliseconds since Epoch.
//            long Purchase = date1.getTime() / 1000;
//           
//            long epoch = System.currentTimeMillis() / 1000;
//            System.out.println("date " + epoch);
//            String createdby = (String) hs.getAttribute("username");
//            String applicationPath = "";
//            // constructs path of the directory to save uploaded file
//            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
////         String uploadFilePath1 = applicationPath + File.separator + UPLOAD_DIR;
//            // creates the save directory if it does not exists
//            File fileSaveDir = new File(uploadFilePath);
//            if (!fileSaveDir.exists()) {
//                fileSaveDir.mkdirs();
//            }
//            System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());
//            Part xl = request.getPart("file");
//
//            Part pic = request.getPart("photo");
//            //Saving XL File
//            if (xl != null) {
//                //writing Data to excel
//                fileName = getFileName(xl);
//
////          POIFSFileSystem fs = new POIFSFileSystem(filecontent);
////            System.out.println("filepath"+fs);
////            HSSFWorkbook wb = new HSSFWorkbook(fs);
////            System.out.println("filepath"+wb);
////            HSSFSheet sheet = wb.getSheetAt(0);
////               System.out.println("sheet"+sheet);
////            Cell cell = null; // declare a Cell object
////              cell=sheet.getRow(0).createCell(7);
////               System.out.println("iowyeoqwiw");
////
////                //Set Result is pass in that cell number
////                cell.setCellValue("Produc-export");
////                cell=sheet.getRow(1).createCell(7);
////                 System.out.println("325423423");
////                //Set Result is pass in that cell number
////                cell.setCellValue(Invoice);
////                filecontent.close();
////                System.out.println("Close Input Stream");
////
//                //close the stream
//                //end
//                File file = new File(uploadFilePath + File.separator + fileName);
//                OutputStream out = new FileOutputStream(file);
//
//                System.out.println("2789312");
//                out.close();
//                fileName = Invoice + fileName;
//            }
//
//            //Saving Photo
//            if (pic != null) {
//                photo = getFileName(pic);
//                File file = new File(uploadFilePath + File.separator + photo);
//                OutputStream out = new FileOutputStream(file);
//                photo = photo;
//                out.close();
//
//            }
//            System.out.println("name=====" + uploadFilePath + File.separator + fileName);
//            // System.out.println("name"+fileName);
//
//           
//            System.out.println(": inserting image ");
//
//            InputStream filecontent = xl.getInputStream();
//            System.out.println("Filecontent=" + filecontent);
//            // request.setAttribute("gurumessage", "File Uploaded Successfully");
//            // FileInputStream input = new FileInputStream("/home/glodeveloper/Desktop" + File.separator + name);
//            {
//                POIFSFileSystem fs = new POIFSFileSystem(filecontent);
//                System.out.println("filepath" + fs);
//                HSSFWorkbook wb = new HSSFWorkbook(fs);
//                System.out.println("filepath" + wb);
//                HSSFSheet sheet = wb.getSheetAt(0);
//                DataFormatter formatter = new DataFormatter();
//                Row row;
//                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
//                    row = sheet.getRow(i);
//                    String Invoice1 = request.getParameter("invoice");
//                    String accountID = row.getCell(0).getStringCellValue();
//                    System.out.println("3093754238975237" + accountID);
//                    String product_branch = row.getCell(1).getStringCellValue();
//                    String product_branchID = gcn.getBranchID(product_branch);
//                    String product_category = row.getCell(2).getStringCellValue();
//                    String product_categoryID = gcn.getCategoryID(product_category);
//                    String product_subcategory = row.getCell(3).getStringCellValue();
//                    System.out.println("sub Product_Category" + product_subcategory);
//
//                    String product_subcategoryID = gcn.getSubcategoryID(product_subcategory);
//                    System.out.println("cat" + product_subcategoryID);
//
//                    //int product_IMEI1 = (int) row.getCell(4).getNumericCellValue();
//                    // System.out.println("imeiimei" + product_IMEI1);
//                    //    String product_IMEI = Integer.toString(product_IMEI1);
//                    String product_IMEI = NumberToTextConverter.toText(row.getCell(4).getNumericCellValue());
//
//                    System.out.println("imei" + product_IMEI);
//                    Random rand = new Random();
//
//                    int rand_int1 = rand.nextInt(1000);
//                    String UniqueID = "PRO" + rand_int1;
//                    System.out.println(UniqueID + "=accountIDDDDDDDDDDDDDDDDDDD");
//                    int Cost = (int) row.getCell(5).getNumericCellValue();
//
//                    // int Cost=45;
//                    System.out.println("2131" + Cost);
//                    Date mfd = row.getCell(6).getDateCellValue();
//
//                    System.out.println("mfd" + mfd);
//                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//                    String strDate = dateFormat.format(mfd);
//                    System.out.println("datre" + strDate);
//                    DateFormat dF1 = new SimpleDateFormat("dd/MM/yyyy"); // The mask
//
//                    // 'a' value in the Mask represents AM / PM - h means hours in AM/PM mode
//                    Date date2 = dF1.parse(strDate); // parsing the String into a Date using the mask
//                    //Date.getTime() method gives you the Long with milliseconds since Epoch.
//                    long ManufactureDate = date2.getTime() / 1000;
//                    // long ManufactureDate=erd.datetoEpoch(mfd);
//                    System.out.println("5456465");
//                    System.out.println("mfd" + ManufactureDate);
//                    System.out.println("23134");
//                    String CompanyName = row.getCell(7).getStringCellValue();
//                    System.out.println("254345  ");
//                    String Version = row.getCell(8).getStringCellValue();
//
//                    //  int Version= 15;
//                    System.out.println("version" + Version);
////                String creation_Time = row.getCell(10).getStringCellValue();
//                    long creation_Time = epoch;
//                    System.out.println("date" + creation_Time);
//                    String created_By = "anudeep";
//                    String ProductDescriptiom = row.getCell(9).getStringCellValue();
//                    String R_S = "available";
//                    String customerID = row.getCell(10).getStringCellValue();
//                    long updation_Time = epoch;
//
//                    //Date d = row.getCell(10).getDateCellValue();
////                String pattern = "dd/MM/yyyy";
////                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
////                String date = simpleDateFormat.format(d);
////                System.out.println("value"+d);
////                 Date date1 = simpleDateFormat.parse(date); // parsing the String into a Date using the mask
////        //Date.getTime() method gives you the Long with milliseconds since Epoch.
////                long Date = date1.getTime() / 1000;
////                System.out.println(date);
////
////                System.out.println(Date);
//                    String sql = "INSERT INTO sm_product VALUES('" + Invoice1 + "','" + accountID + "','" + product_branchID + "','" + product_categoryID + "','" + product_subcategoryID + "','" + product_IMEI + "','" + UniqueID + "','" + Cost + "','" + ManufactureDate + "','" + CompanyName + "','" + Version + "','" + creation_Time + "','" + created_By + "','" + ProductDescriptiom + "','" + R_S + "','" + customerID + "','" + updation_Time + "','" + photo + "' )";
//
//                    pstm = con.getPreparedStatement(sql);
//                    PreparedStatement pstm1 = con.getPreparedStatement("Insert into sm_producthistory(accountID,product_branchID,product_IMEI,creation_Time,created_By,R_S)values(?,?,?,?,?,?)");
//                    System.out.println("sql1=" + pstm1);
//                    System.out.println("sql=" + sql);
//                    pstm1.setString(1, accountID);
//                    pstm1.setString(2, product_branchID);
//                    pstm1.setString(3, product_IMEI);
//                    pstm1.setString(4, String.valueOf(creation_Time));
//                    pstm1.setString(5, created_By);
//                    pstm1.setString(6, R_S);
//
//                    rs = pstm.executeUpdate();
//                    System.out.println("2134214234");
//                    pstm1.executeUpdate();
//                    System.out.println("25423542323523523");
//                    System.out.println("Import rows " + i);
//                }
//
//            }
////                System.out.println("12333432343");
////          request.setAttribute("Response"," Successfully Uploaded Bill");
////            System.out.println("Success import excel to mysql table");
////            RequestDispatcher rd = request.getRequestDispatcher("/jsp/sdb/inward.jsp");
////                rd.forward(request, response);
////
//
//            System.out.println("true" + rs);
//            if (rs > 0) {
//                // request.setAttribute("Response", " Successfully Uploaded Bill");
//                pstm = con.getPreparedStatement("Insert into sm_inward(InvoiceNo,SuppliersName,Quantity,PurchaseDate,File,Photo,createdby,creationtime) values (?,?,?,?,?,?,?,?)");
//                System.out.println(": fgf image ");
//                pstm.setString(1, Invoice);
//                System.out.println("123213" + Invoice);
//                pstm.setString(2, Suppliersname);
//                pstm.setString(3, Quantity);
//                pstm.setString(4, String.valueOf(Purchase));
//                pstm.setString(5, fileName);
//                pstm.setString(6, photo);
//                System.out.println("123213" + photo);
//                pstm.setString(7, createdby);
//                pstm.setString(8, String.valueOf(epoch));
//                int rs1 = pstm.executeUpdate();
//
//                System.out.println("12333432343");
//                request.setAttribute("Response", " Successfully Uploaded Bill");
//                System.out.println("Success import excel to mysql table");
//                RequestDispatcher rd = request.getRequestDispatcher("/jsp/sdb/inward.jsp");
//                rd.forward(request, response);
//
//            } else {
//                request.setAttribute("Response", "Error While Uploading Bill");
//                System.out.println(": ffffff  ");
//                RequestDispatcher rd = request.getRequestDispatcher("/jsp/sdb/inward.jsp");
//                rd.forward(request, response);
//
//            }
//        } catch (Exception e) {
//            System.out.println("Exception" + e.getMessage());
//            request.setAttribute("Response", e.getMessage());
//            RequestDispatcher rd1 = request.getRequestDispatcher("/jsp/sdb/inward.jsp");
//            rd1.forward(request, response);
//            if (con != null) {
//                try {
//                    con.connectionClose();
//                    System.out.println(e.getMessage());
//                    request.setAttribute("Response", e.getMessage());
//                    RequestDispatcher rd = request.getRequestDispatcher("/jsp/sdb/inward.jsp");
//                    rd.forward(request, response);
//                } catch (Exception e1) {
//                }
//            }
//        } finally {
//            if (con != null) {
//                try {
//                    con.connectionClose();
//                } catch (Exception e2) {
//                }
//            }
//        }
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}