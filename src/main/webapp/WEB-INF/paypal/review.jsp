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
  <title>Review</title>
</head>

<body>
  <c:import url="../sharedView/header.jsp"></c:import>
  <br>
  <div class="grid">
    <div class="grid_row">
      <div class="grid_column-6" style="margin: 0 auto; border: 2px solid rgba(215, 219, 221, 0.5); padding: 10px 25px; border-radius: 5px">
        <h3>Please Review Before Paying</h3>
        <hr>
        <form action="execute_payment" method="post">
          <table>
            <tr>
              <td colspan="2"><b>Transaction Details</b></td>
              <td>
                <input type="hidden" name="paymentId" value="${param.paymentId}" />
                <input type="hidden" name="PayerID" value="${param.PayerID}" />
              </td>
            </tr>
            <tr>
              <td>Description:</td>
              <td>${transaction.description}</td>
            </tr>
            <tr>
              <td>Subtotal:</td>
              <td>${transaction.amount.details.subtotal} USD</td>
            </tr>
            <tr>
              <td>Shipping:</td>
              <td>${transaction.amount.details.shipping} USD</td>
            </tr>
            <tr>
              <td>Tax:</td>
              <td>${transaction.amount.details.tax} USD</td>
            </tr>
            <tr>
              <td>Total:</td>
              <td>${transaction.amount.total} USD</td>
            </tr>
            <tr>
              <td><br/></td>
            </tr>
            
            <tr>
              <td colspan="2"><b>Payer Information:</b></td>
            </tr>
            <tr>
              <td>First Name:</td>
              <td>${payer.firstName}</td>
            </tr>
            <tr>
              <td>Last Name:</td>
              <td>${payer.lastName}</td>
            </tr>
            <tr>
              <td>Email:</td>
              <td>${payer.email}</td>
            </tr>
            <tr>
              <td><br /></td>
            </tr>
            <tr>
              <td colspan="2"><b>Shipping Address:</b></td>
            </tr>
            <tr>
              <td style="width: 170px">Recipient Name:</td>
              <td>${shippingAddress.recipientName}</td>
            </tr>
            <tr>
              <td>Line 1:</td>
              <td>${shippingAddress.line1}</td>
            </tr>
            <tr>
              <td>City:</td>
              <td>${shippingAddress.city}</td>
            </tr>
            <tr>
              <td>State:</td>
              <td>${shippingAddress.state}</td>
            </tr>
            <tr>
              <td>Country Code:</td>
              <td>${shippingAddress.countryCode}</td>
            </tr>
            <tr>
              <td>Postal Code:</td>
              <td>${shippingAddress.postalCode}</td>
            </tr>
            <tr>
              <td>
              <br>

              </td>
            </tr>
          </table>
          <hr>
          <div class="form-group">
         		<input type="submit" value="Pay Now" class="btn btn-primary"/>
          </div>
        </form>
      </div>
    </div>
  </div>
  <br>
  <c:import url="../sharedView/footer.jsp"></c:import>
</body>

</html>