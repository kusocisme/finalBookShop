<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="ISO-8859-1">
  <c:import url="../sharedView/global.html" />
  <!-- Custom fonts for this template-->
  <link href="https://ngocthien2306.github.io/Admin-Site/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="https://ngocthien2306.github.io/Admin-Site/css/sb-admin-2.min.css" rel="stylesheet">
  <title>Checkout</title>
</head>

<body>

    <c:import url="../sharedView/header.jsp"></c:import>
    <br>

    <br />
    <div class="grid">
      <div class="grid_row">
        <div class="grid_column-8" style="margin: 0 auto" >
          <h1>Check Out</h1>
          <form action="authorize_payment" method="post">
            <input type="hidden" name="action" value="Pay">
            <div class="form-group">
              <span class="">Product/Service</span>
              <select class="form-control" id="" aria-describedby="emailHelp" name="product"> 
              	   <option value="Rental Book">Rental Book</option>
                   <option value="E-Book">E-Book</option>
                   <option value="Audio Book">Audio Book</option>
              </select>
            </div>
            <div class="form-group">
              <span class="">Sub Total</span>
              <input type="text" class="form-control" id="" aria-describedby="emailHelp" name="subtotal" value="${amount}" readonly="readonly">
            </div>
            <div class="form-group">
              <span class="">Shipping</span>
              <input type="text" class="form-control" id="" aria-describedby="emailHelp" name="shipping" value="${ship}" readonly="readonly">
            </div>
            <div class="form-group">
              <span class="">Tax</span>
              <input type="text" class="form-control" id="" aria-describedby="emailHelp" name="tax" value="${tax}" readonly="readonly">
            </div>
            <div class="form-group">
              <span class="">Total Amount</span>
              <input type="text" class="form-control" id="" aria-describedby="emailHelp" name="total" value="${total}" readonly="readonly">
            </div>
            <div class="form-group">
              <input type="submit" class="btn btn-primary" value="Checkout">
            </div>
          </form>
        </div>
      </div>
    </div>
    <br>
    <c:import url="../sharedView/footer.jsp"></c:import>

</body>

</html>