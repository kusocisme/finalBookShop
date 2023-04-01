package com.paypal;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.UserDTO;
import com.model.User;
import com.paypal.api.payments.Payer;
import com.paypal.base.rest.PayPalRESTException;

import net.bytebuddy.asm.Advice.Local;

@WebServlet("/authorize_payment")
public class AuthorizePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDTO userDto = null;
	private User user = null;
	private Payer payer = null;
	//sb-evtpu8326832@personal.example.com
	//4LC($leR
    public AuthorizePaymentServlet() {
        super();
		userDto = new UserDTO();
		user = new User();
		payer = new Payer();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String action = request.getParameter("action");
    	try {
        	if(action == null) {
        		action = "Check";
        	}
        	switch (action) {
    		case "Check":
    			loadCheckout(request, response);
    			break;
    		case "Pay":
    			doCheckout(request, response);
    		default:
    			break;
    		}
    	}
    	catch (Exception e) {
			e.printStackTrace();
		}
    	finally {
			
		}

		
    }
	private void doCheckout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		String product = request.getParameter("product");
		float subtotal = format.parse(request.getParameter("subtotal")).floatValue();
		float shipping = format.parse(request.getParameter("shipping")).floatValue();
		float tax = format.parse(request.getParameter("tax")).floatValue();
		float total = format.parse(request.getParameter("total")).floatValue();
		
		Checkout check = new Checkout(product, subtotal, shipping, tax, total);
		
		PaymentServices paymentServices = new PaymentServices();
		
		if(user.PayerInfor("ngocthien")) {
			payer = paymentServices.getPayerInformation(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
		}
		
		String approvalLink = "";
		try {
			approvalLink = paymentServices.authorizePayment(check, payer);
			response.sendRedirect(approvalLink);
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("WEB-INF/paypal/404_payment.jsp").forward(request, response);
		}
	}
	private void loadCheckout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		double price = Double.parseDouble(request.getParameter("price")) / 22672;
		Locale locate = new Locale("en", "US");
		NumberFormat cuNumberFormat = NumberFormat.getCurrencyInstance(locate);
		
		double total = price*0.08 + price;
		request.setAttribute("amount",cuNumberFormat.format(price));
		request.setAttribute("total",cuNumberFormat.format(total));
		request.setAttribute("tax",cuNumberFormat.format(price*0.08));
		request.setAttribute("ship",cuNumberFormat.format(0));
		request.getRequestDispatcher("WEB-INF/paypal/checkout.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
