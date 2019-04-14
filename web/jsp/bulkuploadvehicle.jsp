<%-- 
    Document   : bulkuploadvehicle
    Created on : 8 Mar, 2019, 3:30:11 PM
    Author     : glodeveloper
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.uploadoffroad.dbconnection.CreateConnection"%>
<%@page import="com.uploadoffroad.util.EpochtoReadableDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    <!-- Add icon library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"><!------ Include the above in your HEAD tag ---------->
    <link href="../css/upload.css" rel="stylesheet" type="text/css"/>
    <script src="<%=request.getContextPath()%>/js/getvalues.js" type="text/javascript"></script>
    <body onload="getAccounts('<%=request.getContextPath()%>')">

        <script>
            $(document).ready(function () {
                $("#myform").on("submit", function () {
                    $("#pageloader").fadeIn();
                }); //submit
            }); //document ready


        </script>

        <style>
            #pageloader
            {
                background: rgba(255, 255, 255, 0.8);
                display: none;
                height: 100px ;
                position: fixed;
                width: 100px;
                z-index: 9999;
            }

            #pageloader img
            {
                left: 50px ;
                margin-left: - 32px;
                margin-top: - 32px;
                position: absolute;
                top: 50px;
            }
        </style>
        <div class="container register-form">
            <div class="form">
                <div class="note">
                    <h3> Choose File to Upload in Server </h3>
                </div>


                <div id="pageloader">
                    <img src="http://cdnjs.cloudflare.com/ajax/libs/semantic-ui/0.16.1/images/loader-large.gif" alt="processing..." />
                </div>
                <div class="form-content">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="form-group">
                                <form action="<%=request.getContextPath()%>/FileUpload" method="post" id="myform" enctype="multipart/form-data" >
                                    <!-- Trigger the modal with a button -->

                                    <div>
                                        <label name="preview">Preview & Download template</label>
                                        <button type="button" class="btn-primary" data-toggle="modal" data-target="#myModal">Preview</button>
                                    </div>
                                    <br>


                                    <div><label name="preview">Upload template</label>

                                        <select class="form-control" name="accountID" id="accountID" onchange="getGroups('<%=request.getContextPath()%>', this.value)">
                                            <option class="hidden"  selected disabled>Please select accountID</option>


                                        </select>
                                        <br>    
                                        <div>
                                            <div class="form-group">

                                                <select class="form-control" name="groupID" id="groupID" >
                                                    <option class="hidden"  selected disabled>Please select groupID</option>


                                                </select>

                                            </div>

                                        </div>


                                        <div>
                                            <div class="form-group">

                                                <select class="form-control" name="trackerType" id="trackerType">
                                                    <option class="hidden"  selected disabled>Please select Tracker Type</option>
                                                    <option value="bstpl">bstpl</option>
                                                    <option value="tft">tft</option>
                                                    <option value="citgt06">citgt06</option>
                                                    <option value="wtd">wtd</option>

                                                </select>

                                            </div>

                                        </div>

                                        <div>
                                            <div class="form-group">
                                                <label name="ignitionIndex">Ignition Index</label>
                                                <input type="number" name="ignitionIndex" id="ignitionIndex" min="0" placeholder="0"/>

                                            </div>

                                        </div>

                                        <div>
                                            <input type="file" name="file" accept="application/vnd.ms-excel" onchange="checkfile(this);"/>
                                        </div>
                                        <br>
                                        <button type="submit" class="btnSubmit" >Submit</button>

                                        <br><br><br>
                                        <a href="<%=request.getContextPath()%>/jsp/viewdata.jsp">Click here to view all the added Numbers</a> 


                                        <script type="text/javascript" language="javascript">
                                            function checkfile(sender) {
                                                // var validExts = new Array(".xlsx", ".xls", ".csv");
                                                var validExts = new Array(".xls");
                                                var fileExt = sender.value;
                                                fileExt = fileExt.substring(fileExt.lastIndexOf('.'));
                                                if (validExts.indexOf(fileExt) < 0) {
                                                    alert("Invalid file selected, valid files are of " +
                                                            validExts.toString() + " types.");
                                                    return false;
                                                } else
                                                    return true;
                                            }
                                        </script>

                                    </div>
                                </form> 
                            </div>

                        </div>


                        <div class="container">


                            <!-- Modal -->
                            <div class="modal fade" id="myModal" role="dialog">
                                <div class="modal-dialog"  style="width:1250px;overflow-x:auto;">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title"><center>Sample formats for Vehicle uploading </center></h4>
                                        </div>
                                        <div class="modal-body" style="overflow-x:auto;">
                                            <%--<p>Some text in the modal.</p>
                                          <h2>Basic Table</h2>
                                          
                                          <p>The .table class adds basic styling (light padding and only horizontal dividers) to a table:</p> --%>           
                                            <table class="table" id="vehicleInfo">
                                                <thead>
                                                    <tr  class="row-ID" style="background-color:#20557d;color:#FFF;height:10px;">
                                                        <!--                                                <th>S.No</th>-->
                                                        <!--                                                        <th>AccountID</th>-->
                                                        <th>DeviceID</th>

                                                        <th>UniqueID</th>
                                                        <th>ImeiNumber</th>
                                                        <!--                                            <th>serialNumber</th>-->
                                                        <th>SimPhoneNumber</th>
                                                        <th>Baselocation</th>

                                                        <th>OdometerKM</th><!--
                                                                                                   <th>ignitionIndex</th>
                                                                                                   <th>isActive</th>
                                                                                                   <th>description</th>
                                                                                                   <th>groupID</th>
                                                       
                                                                                                   <th>creationTime</th>
                                                                                                   <th>reminderMessage</th>-->


                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <tbody>
                                                    <tr style="color:#000000">

                                                        <%                   EpochtoReadableDate ed = new EpochtoReadableDate();

                                                            CreateConnection con = new CreateConnection();
                                                            Statement stm = con.getStatement();
                                                            String sql = "SELECT * from Device";

                                                            ResultSet rs = stm.executeQuery(sql);

                                                        %> 

                                                        <%                                                String accountID = "gvk-up-108";
                                                            System.out.println(accountID);
                                                            String deviceID = "up41g0205";
                                                            String uniqueID = "862522030420715 ";
                                                            String imeiNumber = "862522030420715";
                                                            String simPhoneNumber = "7233806897";
                                                            String baseLocation = "LUCKNOW";
                                                             String odometerOffsetKM = "50";

                                                        %>

                                                        <%--                                                <td><%= 1%></td> --%>
<!--                                                        <td><%=accountID%></td>-->
                                                        <td><%=deviceID%></td>

                                                        <td><%=uniqueID%></td>
                                                        <td><%=imeiNumber%></td>
                                                        <%-- <td><%=rs.getString("serialNumber")%></td>--> --%>
                                                        <td><%=simPhoneNumber%></td>
                                                        <td><%=baseLocation%></td>
                                                        <td><%=odometerOffsetKM%></td>
                                                       <%-- <td><%=rs.getString("ignitionIndex")%></td>


                                                        <td><%=rs.getString("isActive")%></td>
                                                        <td><%=rs.getString("description")%></td>
                                                        <td><%=rs.getString("groupID")%></td>
                                                        <td><%=rs.getString("creationTime")%></td>
                                                        <td><%=rs.getString("reminderMessage")%></td> --%>



                                                    </tr>

                                                </tbody>
                                            </table>


                                        </div>
                                        <div class="modal-footer">
                                            <button class="btn btn-info" onclick="fnExcelReport();"> <i class="fa fa-download"></i> Download Template</button>
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                        </div>
                                    </div>

                                </div>
                            </div>

                        </div>


                        <script>
                            function fnExcelReport()
                            {
                                //alert("onclick");
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