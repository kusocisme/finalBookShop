package com.DTOs.BusinessDtos;

// Cloudinary authentication key for upload file
public class UploadKeyDTO {
    public String apikey;
    public String cloudname;
    public String folder;
    public String signature;
    public Long timestamp;

    public UploadKeyDTO(String apikey, String cloudname, String folder, String signature, Long timestamp) {
        this.apikey = apikey;
        this.cloudname = cloudname;
        this.folder = folder;
        this.signature = signature;
        this.timestamp = timestamp;
    }

}
