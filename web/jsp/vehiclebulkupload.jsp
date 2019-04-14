<%-- 
    Document   : vehiclebulkupload
    Created on : 8 Feb, 2019, 12:15:45 PM
    Author     : glodeveloper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>File Upload Example in JSP and Servlet - Java web application</title>
    </head>

    <body> 
        <div>
            <h3> Choose File to Upload in Server </h3>



                                <div class="vali-form">
                                    <div class="col-md-6 form-group1">
                                        <label class="control-label">AccountID</label>
                                        <input type="text" name="accountID" id="accountID"  style="background-color:#ccc" placeholder="Enter a value" required readonly >
                                    </div>



                                    <div class="col-md-6 form-group1">
                                        <label class="control-label" >GroupID </label><br>
                                        <select name="groupID" id="groupID" required>
                                            <option value="Select">Select<option>
                                        </select>
                                    </div>



            <form action="<%=request.getContextPath()%>/FileUpload" method="post" enctype="multipart/form-data" >
                <input type="file" name="file" accept="application/vnd.ms-excel" onchange="checkfile(this);"/>
                <input type="submit" value="upload"/>
            </form> 
                

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

    </body>
</html>