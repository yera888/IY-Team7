package com.backend_API.Yarah.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    @Value("${file.upload-dir:src/main/resources/static/uploads}")
    private String uploadDir;

    public FileStorageService() {
    }

    public void init() {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("No upload directory", e);
        }
    }

    @SuppressWarnings("null")
    public String storeFile(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Empty File");
            }

            init();

            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID().toString() + fileExtension;

            Path destinationPath = Paths.get(uploadDir).resolve(newFilename);
            Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            return "/uploads/" + newFilename;
        } catch (IOException e) {
            throw new RuntimeException("Cannot Store File", e);
        }
    }

    public String storeFiles(MultipartFile[] files) {
        List<String> fileUrls = new ArrayList<>();
        
        if (files != null) {
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    String fileUrl = storeFile(file);
                    fileUrls.add(fileUrl);
                }
            }
        }

        return fileUrls.isEmpty() ? "/images/placeholder.jpg" : String.join(",", fileUrls);
    }

    public void deleteFile(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Cannot Delete File", e);
        }
    }
}
