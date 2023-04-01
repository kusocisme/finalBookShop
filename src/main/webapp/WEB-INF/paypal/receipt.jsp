<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <c:import url="../sharedView/global.html" />
  <!-- Custom fonts for this template-->
  <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet">
<title>Payment Receipt</title>
</head>
<body>
<c:import url="../sharedView/header.jsp"></c:import>
<br>
	<div class="grid">
		<div class="grid_row">
			<div class="grid_column-8" style="margin: 0 auto">
				<h3>Payment Done. Thank you for purchasing our products</h3>
    <h4>Receipt Details</h4>
    <br/>
    <table>
        <tr>
            <td><b>Merchant:</b ${id}></td>
            <td>Company ABC Ltd.</td>
        </tr>
        <tr>
            <td><b>Payer:</b></td>
            <td>${payer.firstName} ${payer.lastName}</td>      
        </tr>
        <tr>
            <td style="width: 150px"><b>Description:</b></td>
            <td>${transaction.description}</td>
        </tr>
        <tr>
            <td><b>Subtotal:</b></td>
            <td>${transaction.amount.details.subtotal} USD</td>
        </tr>
        <tr>
            <td><b>Shipping:</b></td>
            <td>${transaction.amount.details.shipping} USD</td>
        </tr>
        <tr>
            <td><b>Tax:</b></td>
            <td>${transaction.amount.details.tax} USD</td>
        </tr>
        <tr>
            <td><b>Total:</b></td>
            <td>${transaction.amount.total} USD</td>
        </tr>                    
    </table>
			</div>
		</div>
		<br>
		<a style="margin: 0 8px;" href="profile">Go to my profile</a>
		<a href="home">Return Homepage</a>
	</div>
	<br>
 <c:import url="../sharedView/footer.jsp"></c:import>
</body>
</html>