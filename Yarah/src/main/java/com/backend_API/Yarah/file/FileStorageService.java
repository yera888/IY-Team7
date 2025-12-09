package com.backend_API.Yarah.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
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

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    private Path uploadPath;

    @PostConstruct
    public void init() {
        try {
            uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                System.out.println("Upload directory created at: " + uploadPath.toAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    public String storeFile(MultipartFile file) {
        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("Empty or null file");
            }

            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null) {
                throw new RuntimeException("File has no name");
            }

            String fileExtension = "";
            int dotIndex = originalFilename.lastIndexOf(".");
            if (dotIndex > 0) {
                fileExtension = originalFilename.substring(dotIndex);
            }
            
            String newFilename = UUID.randomUUID().toString() + fileExtension;
            Path destinationPath = uploadPath.resolve(newFilename);
            
            Files.copy(file.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            
            System.out.println("File stored successfully: " + destinationPath.toAbsolutePath());
            return "/uploads/" + newFilename;
            
        } catch (IOException e) {
            throw new RuntimeException("Cannot store file: " + e.getMessage(), e);
        }
    }

    public String storeFiles(MultipartFile[] files) {
        List<String> fileUrls = new ArrayList<>();
        
        if (files != null && files.length > 0) {
            for (MultipartFile file : files) {
                if (file != null && !file.isEmpty()) {
                    try {
                        String fileUrl = storeFile(file);
                        fileUrls.add(fileUrl);
                    } catch (Exception e) {
                        System.err.println("Error storing file: " + e.getMessage());
                    }
                }
            }
        }

        return fileUrls.isEmpty() ? "/images/placeholder.jpg" : String.join(",", fileUrls);
    }

    public void deleteFile(String filename) {
        try {
            Path filePath = uploadPath.resolve(filename);
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Cannot delete file", e);
        }
    }
}