<%@page import="java.net.URLEncoder"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="com.vnpay.Config"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">

  <!-- Custom fonts for this template-->
  <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet">
        <title>Payment Successfull</title>
        <!-- Bootstrap core CSS -->

        <!-- Custom styles for this template -->
		<c:import url="../sharedView/global.html"></c:import>
        <script src="./js/jquery-1.11.3.min.js"></script>
    </head>
    <body>

        <c:import url="../sharedView/header.jsp"></c:import>
        <!--Begin display -->
		<br>
		<div class="grid">
			<div class="grid_row">
				<div class="grid_row-8" style="margin: 0 auto;">
					
                <h3 class="text-muted">VNPAY RESPONSE</h3>
            <div class="table-responsive">
                <div class="form-group">
                    <label >TransId:</label>
                    <label><%=request.getParameter("vnp_TxnRef")%></label>
                </div>    
                <div class="form-group">
                    <label >Amount:</label>
                    <label><%=request.getParameter("vnp_Amount")%></label>
                </div>  
                <div class="form-group">
                    <label >Order info:</label>
                    <label><%=request.getParameter("vnp_OrderInfo")%></label>
                </div> 
                <div class="form-group">
                    <label >Response Code:</label>
                    <label><%=request.getParameter("vnp_ResponseCode")%></label>
                </div> 
                <div class="form-group">
                    <label >VNPAY TransId:</label>
                    <label><%=request.getParameter("vnp_TransactionNo")%></label>
                </div> 
                <div class="form-group">
                    <label >Bank Code:</label>
                    <label><%=request.getParameter("vnp_BankCode")%></label>
                </div> 
                <div class="form-group">
                    <label >Pay Date:</label>
                    <label><%=request.getParameter("vnp_PayDate")%></label>
                </div> 
                <div class="form-group">
                    <label >Payment Status:</label>
                    <label>${status}</label>
                </div> 
                  <div class="form-group">
                  <a href="profile">Go to my profile</a>
                    <p><a href="home">Return Homepage</a></p>
                    

                </div> 
            </div>
            <p>
                &nbsp;
            </p>

            
        </div> 
				</div>
			</div>
			 
        <c:import url="../sharedView/footer.jsp"></c:import>
    </body>
</html>
