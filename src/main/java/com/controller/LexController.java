package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.ProductDAO;
import com.google.gson.Gson;
import com.model.Product;

@WebServlet("/lex")
public class LexController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LexController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        List<Product> list_product = new ArrayList<Product>();
        ProductDAO productDao = new ProductDAO();

        String type = req.getParameter("type");
        String json = null;
        Gson gson = new Gson();

        try {
            if (type.equals("Popular")) {
                list_product = productDao.getProducts(10);
                json = gson.toJson(list_product);
            } else if (type.equals("New")) {
                list_product = productDao.getProducts(18);
                json = gson.toJson(list_product);
            } else if (type.equals("Sell")) {
                list_product = productDao.getPopularOrder();
                json = gson.toJson(list_product);
            } else if (type.equals("Roman")) {
                list_product = productDao.getRomanceBook();
                json = gson.toJson(list_product);
            } else if (type.equals("Adventure")) {
                list_product = productDao.getAdventureBook();
                json = gson.toJson(list_product);
            } else if (type.equals("Action")) {
                list_product = productDao.getActionBook();
                json = gson.toJson(list_product);
            } else if (type.equals("Business")) {
                list_product = productDao.getBusinessBook();
                json = gson.toJson(list_product);
            }

            PrintWriter response = res.getWriter();
            response.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
