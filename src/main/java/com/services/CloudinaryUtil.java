package com.services;

import java.io.IOException;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

public class CloudinaryUtil {
    public static String apiKey = "714398424555959";
    public static String cloud_name = "dxps1j39b";
    public static String api_secret = "K3RfnJoJwiMquzpV7z-F5hH0Bc0";

    public static Cloudinary getCLoudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", cloud_name, "api_key", apiKey,
                "api_secret", api_secret, "secure", true));
        return cloudinary;
    }

    public static void destroyItem(String publicId) throws IOException {
        Cloudinary cloudinary = CloudinaryUtil.getCLoudinary();
        cloudinary.uploader().destroy(publicId, null);
    }
}
