package com.vnpay;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.*;
import com.model.*;


@WebServlet("/return")
public class returnController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private CartDAO cartDao;
	private OrderDAO orderDao;
	private UserDAO userDao;

    public returnController() {
        cartDao = new CartDAO();
		orderDao = new OrderDAO();
		userDao = new UserDAO();

    }
    @SuppressWarnings("rawtypes")
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String nextUrl = "WEB-INF/vnpay/vnpay_return.jsp";
	String vnp_TransactionStatus = "";
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	Date date = new Date();
	//hash attribute
	try {
	Map fields = new HashMap();
	   for (Enumeration params = request.getParameterNames(); params.hasMoreElements();) {
	       String fieldName = URLEncoder.encode((String) params.nextElement(), StandardCharsets.US_ASCII.toString());
	       String fieldValue = URLEncoder.encode(request.getParameter(fieldName), StandardCharsets.US_ASCII.toString());
	       if ((fieldValue != null) && (fieldValue.length() > 0)) {
	           fields.put(fieldName, fieldValue);
	       }
	   }
	   //get attribute

	   float amount = Float.parseFloat((request.getParameter("vnp_Amount"))) / 100;
	   String code = request.getParameter("vnp_TransactionNo");
	   String day = sdf3.format(timestamp);
	   
	   String vnp_SecureHash = request.getParameter("vnp_SecureHash");
	   if (fields.containsKey("vnp_SecureHashType")) {
	       fields.remove("vnp_SecureHashType");
	   }
	   if (fields.containsKey("vnp_SecureHash")) {
	       fields.remove("vnp_SecureHash");
	   }
	   
	   String signValue = Config.hashAllFields(fields);

	   Object userId = request.getSession().getAttribute("userId");
	   int uid;
	   
	   if(userId == null) {
		   uid = 0;
	   }
	   
	   else {
		   uid = Integer.parseInt(userId.toString());
	   }

	   if (signValue.equals(vnp_SecureHash)) {
	       if ("00".equals(request.getParameter("vnp_TransactionStatus"))) {
	    	   //get user
	    	   User user = userDao.getUser(userId.toString());
	    	   Order order = new Order(day, code, amount, "VNPAY", user);
	    	   orderDao.CreateOrderForUser(order, user);  
	    	   	    	   
	    	   try {
				orderDao.addAllItem(uid, order);
	    	   } 
	    	   catch (Exception e) {
				e.printStackTrace();
	    	   }  		 
		    	   		    	     			    	   
	    	   // remove cart list of user when payment done
	    	   cartDao.RemoveListItemByUserId(uid);

	    	   vnp_TransactionStatus = "Success";
	       } 
	       else {
	    	   	vnp_TransactionStatus = "Failed";
	       }
	   } else {
	   		vnp_TransactionStatus = "Invalid signature";
	   }
	}
	catch (Exception e) {
		nextUrl = "WEB-INF/paypal/404_payment.jsp";
	}
	request.setAttribute("status", vnp_TransactionStatus);
	request.getRequestDispatcher(nextUrl).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
	
	}
}
