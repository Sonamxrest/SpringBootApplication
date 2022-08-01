package com.xrest.spring.Utils;

import java.nio.file.Files;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtils {

    public static String uploadFile(String path , MultipartFile file) {
        try{
            Path path1 = Paths.get(path);
            if(!Files.exists(path1)) {
                    new File(path + file.getOriginalFilename()).mkdirs();
            }
//            Files.write(path1, file.getBytes());
            File file1 = new File(path + file.getOriginalFilename());
            file1.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file1);
            outputStream.write(file.getBytes());
            outputStream.close();
            return path +    file.getOriginalFilename();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return  "";
    }
}
