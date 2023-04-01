package com.paypal;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;


@WebServlet("/review_payment")
public class ReviewPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReviewPaymentServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		
		try {
			PaymentServices paymentServices = new PaymentServices();
			Payment payment = paymentServices.getPaymentDetail(paymentId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			
			request.setAttribute("payer", payerInfo);
			request.setAttribute("transaction", transaction);
			request.setAttribute("shippingAddress", shippingAddress);		
			
			String url = "WEB-INF/paypal/review.jsp?paymentId=" + paymentId +"&PayerID=" + payerId;
			request.getRequestDispatcher(url).forward(request, response);
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("WEB-INF/paypal/paypalError.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
