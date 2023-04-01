package com.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DTOs.BusinessDtos.UploadKeyDTO;
import com.cloudinary.Cloudinary;
import com.google.gson.Gson;
import com.services.CloudinaryUtil;

@WebServlet("/admin/uploadKey")
public class UploadKeyController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Cloudinary cloudinary = CloudinaryUtil.getCLoudinary();

        String type = request.getParameter("type");
        String folder = "";

        if (type.equals("photo")) {
            folder = "bookshop/product/photos";
        } else if (type.equals("video")) {
            folder = "bookshop/product/videos";
        } else if (type.equals("file")) {
            folder = "bookshop/product/files";
        }

        Long timestamp = System.currentTimeMillis() / 1000;

        Map<String, Object> paramsToSign = new HashMap<String, Object>();
        paramsToSign.put("timestamp", timestamp);
        paramsToSign.put("source", "uw");
        paramsToSign.put("folder", folder);

        String signature = cloudinary.apiSignRequest(paramsToSign, CloudinaryUtil.api_secret);

        // Write upload key in json format
        UploadKeyDTO uploadKeyDTO = new UploadKeyDTO(CloudinaryUtil.apiKey, CloudinaryUtil.cloud_name, folder,
                signature, timestamp);
        Gson gson = new Gson();
        String json = gson.toJson(uploadKeyDTO);
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
