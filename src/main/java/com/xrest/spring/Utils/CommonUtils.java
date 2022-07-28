package com.xrest.spring.Utils;


import liquibase.pro.packaged.F;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonUtils {
    public static String uploadFile(String path, MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Path path1 = Paths.get(path );
            if (!Files.exists(path1)) {
                new File(path  + file.getOriginalFilename()).mkdirs();
            }
            File file1 = new File(path + file.getOriginalFilename());
            file1.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file1);
            outputStream.write(bytes);
            outputStream.close();
            return  path  + file.getOriginalFilename();

        } catch (Exception exception) {

        }
        return "";
    }

}
