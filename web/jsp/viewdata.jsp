<%-- 
    Document   : viewdata
    Created on : 22 Feb, 2019, 12:11:20 PM
    Author     : glodeveloper
--%>

<%@page import="com.uploadoffroad.util.EpochtoReadableDate"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="com.uploadoffroad.dbconnection.CreateConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/material-design-lite/1.1.0/material.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="https://cdn.datatables.net/1.10.19/css/dataTables.material.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.material.min.js "></script>



        <title>JSP Page</title>
    </head>
    <script>
        $(document).ready(function () {
            $('#example').DataTable({
                columnDefs: [
                    {
                        targets: [0, 1, 2],
                        className: 'mdl-data-table__cell--non-numeric'
                    }
                ]
            });
        });
    </script>
    <style>
        .mdl-data-table tr th{
            color:#FFFFFF;
        }
    </style>
    <body>
<!--<p>List all: </p>-->
<!--                 <h4>List of the vehicles</h4>-->
        <table id="example" class="mdl-data-table" style="width:100%">
            <thead>
                <tr style="background-color:#3f51b5;color:#FFFFFF;">

                    <th>S.No</th>
                    <th>AccountID</th>
                    <th>DeviceID</th>
                    <th>UniqueID</th>
                    <th>ImeiNumber</th>
                    <th>CreationTime</th>
                </tr>
            </thead>
            <tbody>
                <%
                    EpochtoReadableDate ed = new EpochtoReadableDate();
                    CreateConnection conn = null;
                    Statement stmt = null;
                    ResultSet rs = null;
                    String result = null;

                    try {
                        conn = new CreateConnection();
                        // prepare query
                        String query = "select * from Device ";
                        System.out.println("Query="+ query);
                        // create a statement
                        stmt = conn.getStatement();
                        // execute query and return result as a ResultSet
                        rs = stmt.executeQuery(query);
                        int i = 1;
                        while (rs.next()) {

                            String epoch = rs.getString("creationTime");
                            
                            long convertEpochToLong = Long.parseLong(epoch);
                            

                %>  
                <tr>
                    <td><%=i%></td>
                    <td><%=rs.getString("accountID")%></td>
                    <td><%=rs.getString("deviceID")%></td>
                    <td><%=rs.getString("uniqueID")%></td>
                      <td><%=rs.getString("imeiNumber")%></td>
                    <td><%=ed.newconvertDate(convertEpochToLong)%></td>

                </tr>
                <%  i++;
                        }

                    } catch (Exception e) {
                        e.getMessage();
                        
                    } finally {

                        conn.connectionClose();
                    }

                %>

            <script>
                function Delete(accountID, groupID, vehicleID, offlinetime)
                {
                    

                    var txt;
                    if (confirm("Are you sure you want to delete VehicleID="+vehicleID+"?") === true) {

                        txt = "You pressed OK!";
                        alert(txt);


                        $.ajax({
                            type: "get",
                            url: "<%=request.getContextPath()%>/OffroadVehiclesController?action=delete",
                            data:
                                    {
                                        accountID: accountID,
                                        groupID: groupID,
                                        vehicleID: vehicleID,
                                        offlinetime: offlinetime
                                    },
                            success: function (msg) {
                                alert(msg);
                                location.reload();
                            },
                            error: function (msg)
                            {
                                alert(msg);
                            }

                        });
                    } else {

                        txt = "You pressed Cancel!";
                        alert(txt);
                        return false;
                    }

                }

            </script>



        </tbody>

    </table>

</body>
</html>
