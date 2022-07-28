package com.xrest.spring.Utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUploadUtils {

    private String uploadFile(String path , MultipartFile file) {
        try{
            File file1 = new File(path + file.getOriginalFilename());
            file1.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file1);
            outputStream.write(file.getBytes());
            outputStream.close();
            return path + file.getOriginalFilename();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return  "";
    }
}
