<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>

<!-- Global -->
<c:import url="sharedView/global.html" />

<link rel="stylesheet" href="./css/payment.css" />
<title>Your Cart</title>

</head>

<body>
	<c:import url="sharedView/header.jsp"></c:import>
	<br/>

	<div class="grid">
		<div class="grid_row">
			<div class="grid_column-9">
				<section class="container-cart content-section">
	            <h2 class="section-header">Cart</h2>
	            <div class="cart-row">
	                <span class="cart-item cart-header cart-column">Name</span>
	                <span class="cart-price cart-header cart-column">Price</span>
	                <span class="cart-quantity cart-header cart-column">Quantity</span>

	            </div>
	            <c:forEach var="item" items="${cart.items}">
		            <div class="cart-items">
				        <div class="cart-item cart-column">
				            <img class="cart-item-image" src="${item.getPictureUrl()}">
				            <div class="cart-title">
				        		<p class="name-book"><c:out value="${item.productName}"/></p>
				        		<p class="name-author"><c:out value="${item.getAuthorName()}"/></p>
				        	</div>
				        </div>
				        <div class="cart cart-price cart-column" style="display: flex;">
				        	<p class="price-discount"><c:out value="${item.getPriceDiscount()}"/></p> 
				        </div>
				        <div class="cart-quantity cart-column" >
							<form action="cart" method="post" class="hide-quatity">
								<input type="hidden" name="id" value="${item.id}"/>
				            	<input class="cart-quantity-input" type="number" name="quantity" 
									value="${item.quantity}" readonly/>
								<input type="hidden" name="action" value="UPDATE" />
								<button type="submit" class="btn-update"><i class="fas fa-edit"></i> </button>
							</form>
				            <form action="cart" method="post">
								<input type="hidden" name="id" value="${item.id}" />
								<input type="hidden" name="action" value="REMOVE" />
								<button type="submit" class="btn-remove"><i class="fas fa-trash-alt"></i></button>
							</form>
				        </div>
		            </div>
	            </c:forEach>
	            
	            <div class="cart-total">
	                <strong class="cart-total-title">Total</strong>
	                <span class="cart-total-price"><c:out value="${cart.total}"/></span>	               
	            </div>
	        </section>
			</div>
			<div class="grid_column-3">
				
				<div class="total-price">
					<form action="confirm" method="post">
						 <input id="hide" type="hidden" name="price" value="${cart.total}"/>
						<button type="submit" class="btn btn-primary vnpay">VN PAY</button>
					</form>
					<form action="authorize_payment" method="post">
						 <input id="hide" type="hidden" name="price" value="${cart.total}"/>
						<button type="submit" class="btn btn-primary vnpay"> PAYPAL</button>
					</form>
					
					<div id="smart-button-container">
						<div style="text-align: center;">
							<div id="paypal-button-container"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<a href="home" class="gohome"><button class="btn btn-primary vnpay">Continue Shoping</button></a>
	</div>
	<br/>
	<c:import url="sharedView/footer.jsp"></c:import>
	
	
  <script src="https://www.paypal.com/sdk/js?client-id=AQ5g8nn9aROkHblV7Ljd5PJKV5Tr_cmh4WE6HrGy0GRCUyHOQKuQ7lOqMCdX9D31pAVSVcryYpROoTGY&enable-funding=venmo&currency=USD" data-sdk-integration-source="button-factory"></script>
  <script>

  var price = document.getElementsByClassName("cart cart-price cart-column");
  var total = document.getElementsByClassName("cart-total-price");

//   var priceTotal = 0;
//   for(let i = 0; i < price.length; i++) {
// 	  price[i].textContent = parseFloat(price[i].textContent);
// 	  priceTotal += parseFloat(price[i].textContent);
//   }
  //total[0].textContent = priceTotal;
  document.getElementById("hide").value = priceTotal.toString();
    function initPayPalButton() {
      paypal.Buttons({
        style: {
          shape: 'rect',
          color: 'gold',
          layout: 'vertical',
          label: 'paypal',
          
        },

        createOrder: function(data, actions) {
          return actions.order.create({
            purchase_units: [{"amount":{"currency_code":"USD","value":1}}]
          });
        },

        onApprove: function(data, actions) {
          return actions.order.capture().then(function(orderData) {
            
            // Full available details
            console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));

            // Show a success message within this page, e.g.
            const element = document.getElementById('paypal-button-container');
            element.innerHTML = '';
            element.innerHTML = '<h3>Thank you for your payment!</h3>';

            // Or go to another URL:  actions.redirect('thank_you.html');
            
          });
        },

        onError: function(err) {
          console.log(err);
        }
      }).render('#paypal-button-container');
    }
    initPayPalButton();
  </script>
		
</body>
</html>