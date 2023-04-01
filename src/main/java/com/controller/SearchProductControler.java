package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.ProductDAO;
import com.model.Product;

@WebServlet("/searchProduct")
public class SearchProductControler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SearchProductControler() {
        super();
    }

    private void loadSearchItem(HttpServletRequest req, HttpServletResponse res, String keyword) throws Exception {
        try {
            List<Product> products = new ProductDAO().getByName(keyword);

            req.setAttribute("products", products);
            req.setAttribute("keyword", keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String makeStar(int star) {
        String stars = "";
        String starCheck = "   <i class=\"fas fa-star\"></i>";
        String starUnCheck = "   <i class=\"far fa-star\"></i>";

        for (int i = 0; i < star; i++) {
            stars += starCheck;
        }

        for (int i = 0; i < 5 - star; i++) {
            stars += starUnCheck;
        }

        return stars;
    }

    private void loadSearchAjax(HttpServletRequest req, HttpServletResponse res, String keyword) throws Exception {
        try {
            List<Product> products = new ProductDAO().getByName(keyword);

            PrintWriter response = res.getWriter();
            for (Product product : products) {
                response.println("<div class=\"card\">" + "<a href=\"product?command=LOAD&id=" + product.getId() + "\"style=\"color: 	#77CEED;\">"
                        + "<div class=\"img\">" + " <img src=\" " + product.pictureUrl + " \" />" + " </div>"
                        + "<div class=\"content\">" + "<div class=\"productName\">" + "<h3>" + product.getProductName()
                        + "</h3>" + "</div>" + "<div class=\"price_rating\">" + "    <span> " + product.price
                        + " d</span>" + "    <div class=\"rating\">" + makeStar(product.getStar())
                        + " </div>"
                        + "    </div>" + " </div>" + "   </a>" + "  </div>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String nextUrl = "WEB-INF/productList.jsp";
        res.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        try {
            String action = req.getParameter("action");
            String keyword = req.getParameter("keyword");

            if (action == null)
                action = "LOAD";

            switch (action) {
                case "LOAD":
                    loadSearchItem(req, res, keyword);
                    req.getRequestDispatcher(nextUrl).forward(req, res);
                    break;
                case "AJAX":
                    loadSearchAjax(req, res, keyword);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    }
}
