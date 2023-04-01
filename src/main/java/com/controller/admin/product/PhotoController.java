package com.controller.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.PhotoDAO;
import com.model.Photo;
import com.services.CloudinaryUtil;

@SuppressWarnings("serial")
@WebServlet("/admin/photo")
public class PhotoController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String theCommand = request.getParameter("command");

            switch (theCommand) {
            case "Add":
                addProductPhoto(request, response);
                break;
            case "SetMain":
                setMainProductPhoto(request, response);
                break;
            case "Delete":
                deletePhoto(request, response);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void deletePhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PhotoDAO photoDAO = new PhotoDAO();
        String productId = request.getParameter("productId");
        String photoId = request.getParameter("photoId");
        if (photoId == null) {
            return;
        }
        Photo photo = photoDAO.getPhotoById(Integer.parseInt(photoId));
        CloudinaryUtil.destroyItem(photo.getPublicId());
        photoDAO.deletePhoto(Integer.parseInt(photoId));
        response.sendRedirect("product?command=Load&id=" + productId);
    }

    private void setMainProductPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PhotoDAO photoDAO = new PhotoDAO();
        String photoId = request.getParameter("photoId");
        String productId = request.getParameter("productId");
        if (photoId == null || productId == null) {
            return;
        }
        photoDAO.setMainPhotoProduct(Integer.parseInt(photoId), Integer.parseInt(productId));
        response.sendRedirect("product?command=Load&id=" + productId);
    }

    private void addProductPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PhotoDAO photoDAO = new PhotoDAO();
        String productId = request.getParameter("productId");
        if (productId == null) {
            return;
        }
        Photo photo = new Photo();
        photo.setMain(Boolean.parseBoolean(request.getParameter("isMain")));
        photo.setPublicId(request.getParameter("publicId"));
        photo.setUrl(request.getParameter("url"));
        photoDAO.addPhotoForProduct(photo, Integer.parseInt(productId));
        response.sendRedirect("product?command=Load&id=" + productId);
    }

}
