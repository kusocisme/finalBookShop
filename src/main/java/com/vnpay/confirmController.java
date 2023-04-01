package com.vnpay;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.PromoDAO;
import com.model.Cart;
import com.model.CheckPromo;
import com.model.Promo;


@WebServlet("/confirm")
public class confirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PromoDAO promoDao = null;
    public confirmController() {
        super();
        promoDao = new PromoDAO();
 
    }
    private static final DecimalFormat df = new DecimalFormat("0");
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
	
			// get current action
			String action = request.getParameter("action");
	
			if (action == null) {
				action = "GO";
				
			}
			try 
			{
				int userId = (int) request.getSession().getAttribute("userId");
			} 
			catch (Exception e) {
				response.sendRedirect("login");
				return;
			}
			double price = Double.parseDouble(request.getParameter("price"));
			request.setAttribute("priceTotal", df.format(price));
			
			switch (action) {
			case "CHECK":
				pricePromoCode(request, response);
				break;
			case "BUYNOW":
				try {
				Cart cart = new Cart();
				int cartId = (int) request.getSession().getAttribute("cartId");
				int productId = Integer.parseInt(request.getParameter("productId"));
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				cart.addItem(cartId, productId, quantity);
				String nextUrl = "WEB-INF/vnpay/index_vnpay.jsp";
				request.getRequestDispatcher(nextUrl).forward(request, response);
				}
				catch (Exception e) {
					response.sendRedirect("login");
					return;
				}
				break;
			case "GO":
				directComfirm(request, response);
				break;
			default:
				break;
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		}

		
		
	}

	private void directComfirm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextUrl = "WEB-INF/vnpay/index_vnpay.jsp";
		request.getRequestDispatcher(nextUrl).forward(request, response);
		
	}

	private void pricePromoCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        CheckPromo check = null;
        PrintWriter writer = response.getWriter();
        String status = "";
        int priceDiscount = 0;
			String code = request.getParameter("promoCode");
			int amount = Integer.parseInt(request.getParameter("price"));
			Promo promo = promoDao.getPromoCode(code);
			if(promo != null)  {
				if(Integer.parseInt(promo.getTypeCode()) == 1) {
					priceDiscount = amount - (int) promo.getValue();
				}
				else {
					priceDiscount = amount -  (amount *(int) promo.getValue()) / 100;
				}
				int userId = (int) request.getSession().getAttribute("userId");
				check = new CheckPromo(promo.getId(), userId);
				List<CheckPromo> list = promoDao.checkPromoCode(promo.getId(), userId);
				
				if(list.size() == 0)
					promoDao.addProcodeUsed(check);
				else if (list.size() > 0) {
					priceDiscount = -1;
				}


			}
			else 
				priceDiscount = 0;
			request.setAttribute("priceTotal", priceDiscount);
			writer.println(priceDiscount);
			
			//promoDao.deletePromoCode(promo.getId());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
