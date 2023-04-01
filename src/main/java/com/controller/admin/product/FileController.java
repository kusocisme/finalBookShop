package com.controller.admin.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.data.DAOs.FileDAO;
import com.model.File;
import com.services.CloudinaryUtil;

@SuppressWarnings("serial")
@WebServlet("/admin/file")
public class FileController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String theCommand = request.getParameter("command");

            switch (theCommand) {
            case "Update":
                updateProductFile(request, response);
                break;
            case "Delete":
                deleteFile(request, response);
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

    private void deleteFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileDAO fileDAO = new FileDAO();
        String productId = request.getParameter("productId");
        String fileId = request.getParameter("fileId");
        if (fileId == null) {
            return;
        }
        File file = fileDAO.getFileById(Integer.parseInt(fileId));
        CloudinaryUtil.destroyItem(file.getPublicId());
        fileDAO.deleteFile(Integer.parseInt(fileId));
        response.sendRedirect("product?command=Load&id=" + productId);
    }

    private void updateProductFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileDAO fileDAO = new FileDAO();
        String productId = request.getParameter("productId");
        if (productId == null) {
            return;
        }
        File file = fileDAO.getProductFile(Integer.parseInt(productId));
        if (file != null) {
            fileDAO.deleteFile(file.getId());
            CloudinaryUtil.destroyItem(file.getPublicId());
        }
        file = new File();
        file.setPublicId(request.getParameter("publicId"));
        file.setUrl(request.getParameter("url"));
        fileDAO.addFileForProduct(file, Integer.parseInt(productId));
        response.sendRedirect("product?command=Load&id=" + productId);
    }

}
