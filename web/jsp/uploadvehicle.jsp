<%-- 
    Document   : vehicleregistration
    Created on : Dec 9, 2016, 12:21:33 PM
    Author     : Glodeveloper
--%>

<%@page import="com.uploadoffroad.util.EpochtoReadableDate"%>
<%@page import="com.uploadoffroad.dbconnection.CreateConnection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<!DOCTYPE HTML>
<html>
    <head>
        <title>Vehicle Insertion Form</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Augment Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <!-- Bootstrap Core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

        <link href="common/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
        <!-- Custom CSS -->
        <!--        <link href="common/css/style.css" rel='stylesheet' type='text/css' />-->
        <!-- Graph CSS -->
        <!--        <link href="common/css/font-awesome.css" rel="stylesheet"> -->
        <!-- jQuery -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">

        <!-- lined-icons -->
        <link rel="stylesheet" href="common/css/icon-font.min.css" type='text/css' />
        <!-- //lined-icons -->
        <script src="common/js/jquery-1.10.2.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/fms/getValues.js" type="text/javascript"></script>
        <!--clock init-->
        <%-- for datetimepicker plugin --%>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/fms/datetimepicker/jquery.datetimepicker.css">
        <script src="<%=request.getContextPath()%>/js/fms/jsdatetimepicker/jquery.js"></script>
        <script src="<%=request.getContextPath()%>/js/fms/jsdatetimepicker/jquery.datetimepicker.full.min.js"></script>

        <!--clock init-->
        <script>
            $(function () {

                $("#vehicleregistrationDate,#validFrom,#validTo,#taxValidity,#dateOfEntry").datetimepicker({
                    format: 'd/m/Y',
                    timepicker: false

                });
            });
        </script>
        <style>

            input[type=text], select {
                width: 90%;
                padding:10px;
                display: inline-block;
                /*  border: px solid #ccc;*/
                border-radius: 1px;
                box-sizing: border-box;
            }
            .btn-primary {
                color: #fff;
                background-color: #337ab7;
                border-color: #2e6da4;
            }
            #loading {
                width: 20%;
                height:20%;
                top: 35%;
                left: 45%;
                position: fixed;
                display: block;
                opacity: 0.7;
                background-color: #fff;
                z-index: 99;
                text-align: center;
                background: url('<%=request.getContextPath()%>/images/fms/images/pleasewait.gif') 50% 50% no-repeat rgb(249,249,249);
            }

            #loading-imagenew {
                position: absolute;
                top: 100px;
                align-content: center;
                z-index: 100;
            }

            .required {
                position: absolute;
                margin-left: 10px;
                color: #FB0000;
                font-size: 15px;
            }

            .table tr td {
                white-space: nowrap;
                /*            overflow: hidden;*/
                overflow-x:auto;
                /*            text-overflow: ellipsis;*/
                font-size: 11px;
                /*            height:25px;*/
                font-weight: 100;
                color:#fff;
            }
            .row-ID {
                width: 100%;
                font-size: 12px;

            }
            .row-name {
                width: 30%;
                font-size: 2px;
            }
            tr:nth-child(even) {
                background: #CCC;
            }
            tbody
            {
                overflow:scroll;
            }
            .btn-group-lg > .btn, .btn-lg {
                padding: 9px 8px;
                font-size: 10px;
                line-height: 1.3333333;
                border-radius: 3px;
            }
            .table > thead > tr > th {

                color: #fff;

            }


        </style>

        <script>
            jQuery(document).ready(function () {

                jQuery("[required]").after("<span class='required'>*</span>");
            });
        </script>


    <body onload="getGroups()">
        <%
            CreateConnection con = new CreateConnection();
            if (session.getAttribute("accountID") == null) {
                RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
                rd.forward(request, response);
            } else {

                Map<String, Integer> hm = (Map<String, Integer>) request.getSession().getAttribute("access");

                HttpSession hs = request.getSession();

                String accountID = (String) session.getAttribute("accountID");
                System.out.println(accountID + "=account");
                String roleid = (String) session.getAttribute("roleid");
                System.out.println(roleid + "=roleid");
                String userID = (String) session.getAttribute("userID");
                System.out.println(userID + "=userID");


        %>

        <div class="left-content">

            <div class="inner-content">
                <!-- //header-ends -->
                <!--//outer-wp-->
                <div class="outter-wp">
                    <!--/sub-heard-part-->
                    <div class="sub-heard-part">
                        <ol class="breadcrumb m-b-0">
                            <li><a href="<%=request.getContextPath()%>/fmsdashboard">Home</a></li>
                            <li class="active">Vehicle Insertion Form</li>
                            <li><a href="<%=request.getContextPath()%>/vehicleupload">Bulk upload  </a></li>
                            <li><a href="<%=request.getContextPath()%>/listvehicleinfo">View all Vehicle Information </a></li>

                        </ol>
                    </div>	
                    <!--/sub-heard-part-->	
                    <!--/forms-->
                    <div class="forms-main">
                        <h3 style="text-align:center; font-weight:bold; font-size:20px; color:#00BCD4;">Vehicle Bulk upload Form</h3>
                        <div class="graph-form">
                            <div class="validation-form" name="form">
                                <!---->




                                <h4>Vehicle Details </h4><hr>


                                <div class="col-md-6 form-group1">
                                    <label class="control-label" >Preview template </label><br>
                                    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Click to View</button>

                                    <!--                                    <button type="submit" id="vehiclesubmit" class="btn btn-info">view</button>-->
                                </div> 


                                <div class="vali-form">
                                    <div class="col-md-6 form-group1">
                                        <label class="control-label">AccountID</label>
                                        <input type="text" name="accountID" id="accountID" value="<%=accountID%>" style="background-color:#ccc" placeholder="Enter a value" required readonly >
                                    </div>



                                    <div class="col-md-6 form-group1">
                                        <label class="control-label" >GroupID </label><br>
                                        <select name="groupID" id="groupID" required>
                                            <option value="Select">Select<option>
                                        </select>
                                    </div>






                                    <div class="col-md-6 form-group1">
                                        <label class="control-label" required>Select file to upload
                                        </label><br>
                                        <input type="file"  name="uploadVehicle" id="uploadVehicle" placeholder="Enter a value" >

                                    </div>
                                </div>
                                <div class="col-md-12 form-group button-2">
                                    <center>
                                        <button type="submit" id="vehiclesubmit" class="btn btn-primary">Submit</button>


<!--                                        <button type="reset" id="reset" class="btn btn-default" onclick="submitForm()">Reset</button>-->
                                    </center>
                                </div>
                                <div class="clearfix"> </div>


                                <script>
                                    $("#vehiclesubmit").click(function () {

                                        var accountID = document.getElementById("accountID").value;
                                        alert(accountID);
                                        if (accountID === "")
                                        {
                                            alert("Please enter accountID");
                                            return false;
                                        }
                                        var groupID = document.getElementById("groupID").value;
                                        if (groupID === "Select")
                                        {
                                            alert("Please choose groupID from the given options");
                                            return false;
                                        }


                                        //var vehicleImage = document.getElementById("vehicleImage").value;



                                        //alert(vehicleImage);
                                        var uploadVehicleData = $("#uploadVehicle").prop("files")[0];

                                        if (typeof uploadVehicleData === "undefined")
                                        {
                                            alert("Please upload excel data");
                                            return false;
                                        }



                                        document.getElementById("loading").style.display = "block";


                                        var form_data = new FormData();
                                        form_data.append("accountID", accountID);
                                        form_data.append("groupID", groupID);

                                        form_data.append("uploadVehicleData", uploadVehicleData);
                                        form_data.append("sendtype", "add");



                                        $.ajax({
                                            type: "post",
                                            url: "<%=request.getContextPath()%>/BulkUploadVehicleController",

                                            data:
                                                    form_data
                                            ,
                                            cache: false,
                                            contentType: false,
                                            processData: false,

                                            success: function (msg)
                                            {
                                                document.getElementById("loading").style.display = "none";
                                                alert(msg);
                                                if (msg.length === 19)
                                                {
                                                    location.reload("<%=request.getContextPath()%>/VehicleController");
                                                }
                                            },
                                            error: function (msg)
                                            {
                                                alert(msg + "=error");
                                                location.reload();
                                            }

                                        });
                                    });

                                </script>






                                <!--                                  VEHICLE IMAGE
                                <div class="col-md-6 form-group1">
                                                                            <label class="control-label">Vehicle Image 
                                                                                <input type="file" id="vehicleImage"  name="image"  required style="width:100%;">
                                                                                </div>
                                
                                                                                </div>
                                
                                                                                <div class="col-md-6 form-group1">
                                                                                    <label class="control-label">Vehicle Registration Certification Proof
                                                                                        <input type="file" id="vehicleRegistrationCertifaction"  name="vehicleRegistrationCertifaction" required style="width:100%;">
                                                                                        </div>-->

                                <%--for GIF loading --%>    
                                <div id="loading" style="display:none;">

                                    <%--  <img id="loading-image" src="<%=request.getContextPath()%>/images/fms/images/loading.gif" alt="Please wait...!" /> --%>
                                </div> 


                                <div class="clearfix"> </div>
                            </div> </div>

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
                                                <tr  class="row-ID" style="background-color:#20557d;color:#fff;height:10px;">
                                                    <th >S.No</th>
                                                    <!--                                                    <th >AccountID</th>
                                                                                                        <th >GroupID</th>-->
                                                    <th >VehicleID</th>
                                                    <th >VehicleClass</th>
                                                    <th >RegisteredOwner</th>
                                                    <th >CareOf</th>
                                                    <th>PresentAddress</th>
                                                    <th>PermanentAddress</th>
                                                    <th >VehicleregistrationDate</th>
                                                    <th >ValidFrom</th>
                                                    <th >ValidTo</th>

                                                    <th style="color:#fff;">VehicleMake</th>
                                                    <th style="color:#fff;">VehicleModelID</th>
                                                    <th style="color:#fff;">VehicleManufactureDate</th>



                                                    <th>BodyType</th>
                                                    <th>NoOfCylinder</th>
                                                    <th>EngineNo</th>
                                                    <th>EngineType</th>
                                                    <th>ChasisNumber</th>
                                                    <th>HorsePower</th>
                                                    <th>CubicCapacity</th>
                                                    <th>MakersClassification</th>
                                                    <th>WheelBase</th>
                                                    <th>SeatingCapacity</th>
                                                    <th>UnladenWeight</th>
                                                    <th>ColorOfBodyandWings</th>
                                                    <th>GrossVehicleWeight</th>
                                                    <th>TyreNumber</th>
                                                    <th>FrontAxle</th>
                                                    <th>RearAxle</th>
                                                    <th>TandemAxle</th>

                                                    <th>FrontAxleWeight</th>
                                                    <th>RearAxleWeight</th>
                                                    <th>TandemAxleWeight</th>
                                                    <th>TaxPaid</th>
                                                    <th>TaxValidity</th>
                                                    <th>State</th>
                                                    <th>District</th>
                                                    <th>FuelType</th>
                                                    <th>FuelCapacity</th>
                                                    <th>Mileage(km/l)</th>
                                                    <th>DateOfEntry</th>
                                                    <th>Remarks</th>
                                                    <th>Signature</th>


                                                </tr>
                                            </thead>
                                            <tbody>
                                            <tbody>
                                                <tr style="color:#000000">

                                                    <%                   EpochtoReadableDate ed = new EpochtoReadableDate();

                                                        try {
//                                                            CreateConnection con = new CreateConnection();
                                                            Statement stm = con.getStatement();
                                                            String sql = "SELECT * from fms_autoVehicles where accountID='" + accountID + "' limit 5";
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
                                                        while (rs.next()) {%>

                                                    <td><%= i%></td>
<!--                                                    <td><%=rs.getString("accountID")%></td>
                                                    <td><%=rs.getString("groupID")%></td>-->
                                                    <td><%=rs.getString("vehicleID")%></td>
                                                    <td><%=rs.getString("vehicleClass")%></td>
                                                    <td><%=rs.getString("registeredOwner")%></td>
                                                    <td><%=rs.getString("careOf")%></td>
                                                    <td><%=rs.getString("presentAddress")%></td>
                                                    <td><%=rs.getString("permanentAddress")%></td>
                                                    <td><%=ed.convertTime(Long.valueOf(rs.getString("vehicleregistrationDate")))%></td>
                                                    <td><%=ed.convertTime(Long.valueOf(rs.getString("validFrom")))%></td>
                                                    <td><%=ed.convertTime(Long.valueOf(rs.getString("validTo")))%></td>



                                                    <td><%=rs.getString("vehicleMake")%></td>
                                                    <td><%=rs.getString("vehicleModelID")%></td>
                                                    <td><%=rs.getString("vehicleManufactureDate")%></td>
                                                    <td><%=rs.getString("bodyType")%></td>
                                                    <td><%=rs.getString("noOfCylinder")%></td>
                                                    <td><%=rs.getString("engineNo")%></td>
                                                    <td><%=rs.getString("engineType")%></td>
                                                    <td><%=rs.getString("chasisNumber")%></td>
                                                    <td><%=rs.getString("horsePower")%></td>
                                                    <td><%=rs.getString("cubicCapacity")%></td>
                                                    <td><%=rs.getString("makersClassification")%></td>
                                                    <td><%=rs.getString("wheelBase")%></td>
                                                    <td><%=rs.getString("seatingCapacity")%></td>
                                                    <td><%=rs.getString("unladenWeight")%></td>
                                                    <td><%=rs.getString("colorOfBodyandWings")%></td>
                                                    <td><%=rs.getString("grossVehicleWeight")%></td>
                                                    <td><%=rs.getString("tyreNumber")%></td>
                                                    <td><%=rs.getString("frontAxle")%></td>
                                                    <td><%=rs.getString("rearAxle")%></td>
                                                    <td><%=rs.getString("tandemAxle")%></td>
                                                    <td><%=rs.getString("frontAxleWeight")%></td>
                                                    <td><%=rs.getString("rearAxleWeight")%></td>
                                                    <td><%=rs.getString("tandemAxleWeight")%></td>



                                                    <td><%=rs.getString("taxPaid")%></td>
                                                    <td><%=ed.convertTime(Long.valueOf(rs.getString("taxValidity")))%></td>

                                                    <td><%=rs.getString("state")%></td>
                                                    <td><%=rs.getString("district")%></td>
                                                    <td><%=rs.getString("fuelType")%></td>
                                                    <td><%=rs.getString("fuelCapacity")%></td>
                                                    <td><%=rs.getString("mileagePerHour")%></td>
                                                    <td><%=ed.convertTime(Long.valueOf(rs.getString("dateOfEntry")))%></td>
                                                    <td><%=rs.getString("remarks")%></td>
                                                    <td><%=rs.getString("Signature")%></td>
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

                    <div id="map"></div>
                    <script>
                        function initMap() {
                            var map = new google.maps.Map(document.getElementById('map'), {
                            });

                            var input = (document.getElementById('state'));
                            var input1 = (document.getElementById('district'));

                            var input2 = (document.getElementById('presentAddress'));
                            var input3 = (document.getElementById('permanentAddress'));
                            //var input1 = /** @type {!HTMLInputElement} */(
                            //  document.getElementById('destination'));


                            var autocomplete = new google.maps.places.Autocomplete(input);
                            autocomplete.bindTo('bounds', map);
                            var autocomplete1 = new google.maps.places.Autocomplete(input1);

                            autocomplete1.bindTo('bounds', map);

                            var autocomplete2 = new google.maps.places.Autocomplete(input2);
                            autocomplete2.bindTo('bounds', map);
                            var autocomplete3 = new google.maps.places.Autocomplete(input3);
                            autocomplete3.bindTo('bounds', map);


                        }

                    </script>
                    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB7n5H8AdWMswtI1MYbTlpqE3tj5txyAQw&libraries=places&callback=initMap"
                    async defer></script>









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


                    <!---->
                </div>

            </div>
        </div> 
        <!--//forms-->											   
    </div>
    <!--//outer-wp-->
    <!--footer section start-->

    <!--js -->
    <script src="common/js/jquery.nicescroll.js"></script>
    <script src="common/js/scripts.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="common/js/bootstrap.min.js"></script>
    <%
            con.connectionClose();

        }
    %>
</body>
</html>