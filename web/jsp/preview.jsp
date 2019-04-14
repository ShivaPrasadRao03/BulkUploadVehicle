<%-- 
    Document   : preview
    Created on : 8 Feb, 2019, 3:31:40 PM
    Author     : glodeveloper
--%>

<%@page import="java.sql.Statement"%>
<%@page import="com.uploadoffroad.dbconnection.CreateConnection"%>
<%@page import="com.uploadoffroad.util.EpochtoReadableDate"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Bootstrap Example</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <!-- Add icon library -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>

    <style>


        .table tr td {
            white-space: nowrap;
            /*            overflow: hidden;*/
            overflow-x:auto;
            /*            text-overflow: ellipsis;*/
            font-size: 11px;
            /*            height:25px;*/
            font-weight: 100;
        }
        .row-ID {
            width: 100%;
            font-size: 12px;

        }
        .row-name {
            width: 40%;
            font-size: 2px;
        }
        tr:nth-child(even) {
            background: #CCC;
        }
        tbody
        {
            overflow:scroll;
        }




    </style>
    <body>

        <div class="container">
            <!-- Trigger the modal with a button -->
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Open Modal</button>

            <!-- Modal -->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog"  style="width:1250px;overflow-x:auto;">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title"><center>Sample format for Vehicle uploading </center></h4>
                        </div>
                        <div class="modal-body" style="overflow-x:auto;">
                            <%--<p>Some text in the modal.</p>
                          <h2>Basic Table</h2>
                          
                          <p>The .table class adds basic styling (light padding and only horizontal dividers) to a table:</p> --%>           
                            <table class="table" id="vehicleInfo">
                                <thead>
                                    <tr  class="row-ID" style="background-color:#20557d;color:#FFF;height:10px;">
                                        <th>S.No</th>
                                        <th>AccountID</th>
                                        <th>deviceID</th>
                                        <th>uniqueID</th>
                                        <th>imeiNumber</th>
<!--                                        <th>serialNumber</th>-->
                                        <th>simPhoneNumber</th>
                                        <th>odometerOffsetKM</th>
                                        <th>ignitionIndex</th>
                                        <th>isActive</th>
                                        <th>description</th>
                                        <th>groupID</th>

                                        <th>creationTime</th>
                                        <th>reminderMessage</th>


                                    </tr>
                                </thead>
                                <tbody>
                                <tbody>
                                    <tr style="color:#000000">

                                        <%                   EpochtoReadableDate ed = new EpochtoReadableDate();

                                            try {
                                                CreateConnection con = new CreateConnection();
                                                Statement stm = con.getStatement();
                                                String sql = "SELECT * from Device";
                                                //                                                String sql = "";
                                                //                                                if (groupID.equalsIgnoreCase("select")) {
                                                //                                                    sql = "SELECT * FROM fms_autoVehicles where accountID='" + accountID + "'  AND dateOfEntry between '" + fromDateEpoch + "' and '" + toDateEpoch + "'";
                                                //                                                    System.out.println(sql);
                                                //                                                } else if (vehicleID.equalsIgnoreCase("selectall")) {
                                                //                                                    sql = "SELECT * FROM fms_autoVehicles where accountID='" + accountID + "'  AND groupID='" + groupID + "'  AND dateOfEntry between '" + fromDateEpoch + "' and '" + toDateEpoch + "'";
                                                //                                                    System.out.println(sql);
                                                //                                                } else {
                                                //                                                    sql = "SELECT * FROM fms_autoVehicles where accountID='" + accountID + "' AND groupID='" + groupID + "' AND vehicleID='" + vehicleID + "' AND dateOfEntry between '" + fromDateEpoch + "' and '" + toDateEpoch + "'";
                                                //                                                    System.out.println(sql + "=2");
                                                //                                                }
                                                ResultSet rs = stm.executeQuery(sql);

                                        %> 

                                        <% int i = 1;
                                            while (rs.next()) {

                                                String accountID = "gvk-up-108";
                                                String deviceID = "up41g0205";
                                                String uniqueID = "tft_862522030420715 ";
                                                String imeiNumber = "862522030420715";
                                                String simPhoneNumber = "7233806897";
                                                String baseLocation = "LUCKNOW";


                                        %>

                                        <td><%= i%></td>
                                        <td><%=accountID%></td>
                                        <td><%=deviceID%></td>
                                        <td><%=uniqueID%></td>
                                        <td><%=imeiNumber%></td>
                                        <td><%=simPhoneNumber%></td>
                                         <td><%=baseLocation%></td>
                                      <%--  <td><%=rs.getString("simPhoneNumber")%></td>
                                        <td><%=rs.getString("odometerOffsetKM")%></td>
                                        <td><%=rs.getString("ignitionIndex")%></td>


                                        <td><%=rs.getString("isActive")%></td>
                                        <td><%=rs.getString("description")%></td>
                                        <td><%=rs.getString("groupID")%></td>
                                        <td><%=rs.getString("creationTime")%></td>
                                        <td><%=rs.getString("reminderMessage")%></td> 

<!--                                        <td><%=ed.convertTime(Long.valueOf(rs.getString("taxValidity")))%></td> --%>


                                    </tr>
                                    <% i++;;%>
                                    <%
                                            }

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    %>	
                                </tbody>
                            </table>


                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-primary" onclick="fnExcelReport();"> <i class="fa fa-download"></i> Download Template</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                    </div>

                </div>
            </div>

        </div>


        <script>
            function fnExcelReport()
            {
                alert("onclick");
                var tab_text = "<table border='2px'><tr bgcolor='#87AFC6'>";
                var textRange;
                var j = 0;
                var tab = document.getElementById('vehicleInfo'); // id of table
                alert(tab);
                for (j = 0; j < tab.rows.length;
                        j++
                        )
                {
                    tab_text = tab_text + tab.rows[j].innerHTML + "</tr>";
                    //tab_text=tab_text+"</tr>";
                }

                tab_text = tab_text + "</table>";

                tab_text = tab_text.replace(/<A[^>]*>|<\/A>/g, ""); //remove if u want links in your table
                tab_text = tab_text.replace(/<img[^>]*>/gi, ""); // remove if u want images in your table
                tab_text = tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

                var ua = window.navigator.userAgent;
                var msie = ua.indexOf("MSIE ");
                if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
                {
                    txtArea1.document.open("txt/html", "replace");
                    txtArea1.document.write(tab_text);
                    txtArea1.document.close();
                    txtArea1.focus();
                    sa = txtArea1.document.execCommand("SaveAs", true, "Say Thanks to Sumit.xls");
                } else                 //other browser not tested on IE 11
                    sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));
                return (sa);
            }

        </script>

    </body>
</html>

