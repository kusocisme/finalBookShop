package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.ReviewDAO;
import com.data.DAOs.UserDAO;
import com.model.Product;
import com.model.Review;

@WebServlet("/review")
public class ReviewController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReviewController() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String prevPath = req.getContextPath() + "/product?command=LOAD&id=";
        String responseMessage = "Fail";

        String productId = req.getParameter("productId");
        String date = null, userId = null, reviewContent = null, reviewId = null;
        int starsRating = 0;

        if (action == null) {
            action = "Go to productItem.jsp";
        }

        switch (action) {
            case "CREATE":
                UserDAO userDAO = new UserDAO();
                starsRating = req.getParameter("rating") == null ? 1 : Integer.parseInt(req.getParameter("rating"));
                reviewContent = req.getParameter("review-content");
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                date = formatter.format(new Date());
                userId = req.getSession().getAttribute("userId").toString();

                if (productId == null || userId == null) {
                    req.getRequestDispatcher("WEB-INF/home.jsp").forward(req, res);
                    return;
                }

                Review review = new Review(date, reviewContent, userDAO.getUser(userId),
                        Product.find(Integer.parseInt(productId)), starsRating);

                if (Review.createReview(review) != null) {
                    System.out.println("Create review success!!!");
                } else {
                    System.out.println("Error when trying to create new review!!!");
                }

                res.sendRedirect(prevPath + productId);
                break;

            case "UPDATE":
                starsRating = Integer.parseInt(req.getParameter("stars"));
                reviewContent = req.getParameter("content");
                reviewId = req.getParameter("reviewId");

                Review update = Review.getReview(reviewId);
                update.setContent(reviewContent);
                update.setStars(starsRating);

                if (Review.updateReview(update)) {
                    responseMessage = update.getContent();
                } else {
                    responseMessage = "500";
                }

                res.setContentType("text/html;charset=UTF-8");
                res.getWriter().write(responseMessage);

                break;

            case "DELETE":
                reviewId = req.getParameter("reviewId");

                if (Review.getReview(reviewId) == null) {
                    responseMessage = "500";
                } else {
                    Review delete = Review.deleteReview(reviewId);
                    responseMessage = "Delete success!";
                }

                res.setContentType("text/html;charset=UTF-8");
                res.getWriter().write(responseMessage);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String reviewId = req.getParameter("reviewId");
        res.setContentType("text/html;charset=UTF-8");
        String reviewContent = "Fail";

        try {
            ReviewDAO reviewDAO = new ReviewDAO();
            reviewContent = reviewDAO.getReview(reviewId).getContent();

        } catch (Exception e) {
            e.printStackTrace();
        }

        res.getWriter().write(reviewContent);
    }
}
