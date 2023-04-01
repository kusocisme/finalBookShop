package com.paypal;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.CartDAO;
import com.data.DAOs.OrderDAO;
import com.data.DAOs.ProductDAO;
import com.data.DAOs.UserDAO;
import com.model.Order;
import com.model.User;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;

@WebServlet("/execute_payment")
public class ExecutePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CartDAO cartDao;
	private OrderDAO orderDao;
	private UserDAO userDao;
	private ProductDAO productDao;

    public ExecutePaymentServlet() {
        super();
        cartDao = new CartDAO();
		orderDao = new OrderDAO();
		userDao = new UserDAO();
		productDao = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		Timestamp ts = Timestamp.from(Instant.now());
		
		try {
			PaymentServices paymentServices = new PaymentServices();
			Payment payment = paymentServices.executePayment(paymentId, payerId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction = payment.getTransactions().get(0);
			
			Object userId = request.getSession().getAttribute("userId");
			int uid;
			if(userId == null) {
				uid = 0;
			}   
			else {
				uid = Integer.parseInt(userId.toString());
			}
			
			float amount = Float.parseFloat(transaction.getAmount().getTotal());
			
	    	User user = userDao.getUser(userId.toString());
	    	Order order = new Order(ts.toString(), payerId, amount, "PAYPAL", user);
	    	orderDao.CreateOrderForUser(order, user);  

			orderDao.addAllItem(uid, order);
			cartDao.RemoveListItemByUserId(uid);
			
			request.setAttribute("payer", payerInfo);
			request.setAttribute("id", uid);
			request.setAttribute("transaction", transaction);
			
			
			request.getRequestDispatcher("WEB-INF/paypal/receipt.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("WEB-INF/paypal/404_payment.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
