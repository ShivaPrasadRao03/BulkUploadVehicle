<%-- 
    Document   : login
    Created on : 8 Mar, 2019, 3:29:44 PM
    Author     : glodeveloper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!------ Include the above in your HEAD tag ---------->

    <body>

        
        <div class="container register">
            <div class="row">
                <div class="col-md-3 register-left">
                    <!--                        <img src="" alt=""/>-->
                    <h3>Welcome</h3>

                    <!--                        <input type="submit" name="" value="Login"/><br/>-->
                </div>
                <div class="col-md-9 register-right">
                    <ul class="nav nav-tabs nav-justified" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Register</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Login</a>
                        </li>
                    </ul>
                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                            <h3 class="register-heading">Register</h3>
                            <div class="row register-form">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="text" name="firstName" id="firstName" class="form-control" placeholder="First Name *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="text" name="lastName" id="lastName" class="form-control" placeholder="Last Name *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="password" id="cpassword" class="form-control" placeholder="Password *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="confirmPassword" id="confirmPassword" class="form-control"  placeholder="Confirm Password *" value="" />
                                    </div>
                                    
                                    
                                    <!--                                                        <div class="form-group">
                                                                                                <div class="maxl">
                                                                                                    <label class="radio inline"> 
                                                                                                        <input type="radio" name="gender" value="male" checked>
                                                                                                        <span> Male </span> 
                                                                                                    </label>
                                                                                                    <label class="radio inline"> 
                                                                                                        <input type="radio" name="gender" value="female">
                                                                                                        <span>Female </span> 
                                                                                                    </label>
                                                                                                </div>
                                                                                            </div>-->
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="email" id="email" class="form-control" placeholder="Your Email *" value="" />
                                    </div>
                                    <div class="form-group">
                                        <input type="text" minlength="10" maxlength="10" name="phoneNumber" class="form-control" placeholder="Your Phone *" value="" />
                                    </div>
                                    <!--                                                         <div class="form-group">
                                                                                                <select class="form-control">
                                                                                                    <option class="hidden"  selected disabled>Please select your Sequrity Question</option>
                                                                                                    <option>What is your Birthdate?</option>
                                                                                                    <option>What is Your old Phone Number</option>
                                                                                                    <option>What is your Pet Name?</option>
                                                                                                </select>
                                                                                            </div>
                                                                                            <div class="form-group">
                                                                                                <input type="text" class="form-control" placeholder="Enter Your Answer *" value="" />
                                                                                            </div> -->
                                    <input type="submit" class="btnRegister"  value="Submit"/>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane fade show" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                            <h3  class="register-heading">Login</h3>
                            <div class="row register-form" align="center">
                                <div class="col-md-6" >
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="Account Name *" name="accountID" id="accountID"  />
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control" placeholder="User Name *" name="username" id="username"  />
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" placeholder="Password *"  name="password" id="password"  />
                                    </div>
                                    <!--                                        <div class="form-group">
                                                                                <input type="text" maxlength="10" minlength="10" class="form-control" placeholder="Phone *" value="" />
                                                                            </div>-->
                                    <input type="submit" class="btnRegister"  id="submit" value="Login"/> 

                                    <script>
                                        $('#submit').click(function () {
                                        var accountID = document.getElementById("accountID").value;
                                        var username = document.getElementById("username").value;
                                        var password = document.getElementById("password").value;
                                       
                                       accountID="glovision";
                                       username="admin";
                                       password="admin@g.co";
                                       
                                       
                                        $.ajax({
                                        type: "post",
                                                url: "<%=request.getContextPath()%>/login", //this is my servlet
                                                data: {
                                                accountID: accountID,
                                                        username: username,
                                                        password: password
                                                },
                                                success: function (msg) {

                                                //alert(msg);
                                                var x = msg;
                                                if (x.length === 6)
                                                {

                                                 window.location.href = "<%=request.getContextPath()%>/jsp/bulkuploadvehicle.jsp";
                                                } else if (x.length === 10)
                                                {
                                                //alert("Incorrect Login Credentials");
                                                 window.location.href = "<%=request.getContextPath()%>/jsp/bulkuploadvehicle.jsp";
                                                //window.location.reload();
                                                }


                                                },
                                                error: function (msg) {

                                                location.reload();
                                                }
                                        });
                                        });
                                    </script> 
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>