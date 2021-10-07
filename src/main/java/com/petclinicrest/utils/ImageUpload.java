package com.petclinicrest.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ImageUpload {


    public static Map<FileUpload, Object> upload(MultipartFile file) {
        long maxFileUploadSize = 2048;
        String errorMessage = "";
        Map<FileUpload, Object> hm = new LinkedHashMap<>();
        if (!file.isEmpty() ) {
            long fileSizeMB = file.getSize() / 1024;
            if ( fileSizeMB > maxFileUploadSize ) {
                System.err.println("Dosya boyutu çok büyük Max 2MB");
                errorMessage = "Dosya boyutu çok büyük Max "+ (maxFileUploadSize / 1024) +"MB olmalıdır";
            }else {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                String ext = fileName.substring(fileName.length()-5, fileName.length());
                String uui = UUID.randomUUID().toString();
                fileName = uui + ext;
                try {
                    String UPLOAD_DIR = "src/main/resources/static/profile_images/";
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    hm.put(FileUpload.result, fileName);
                } catch ( IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            errorMessage = "Lütfen resim seçiniz!";
        }

        if ( errorMessage.equals("") ) {
            hm.put(FileUpload.status, true);
        }else {
            hm.put(FileUpload.status, false);
        }

        return hm;
    }


}
