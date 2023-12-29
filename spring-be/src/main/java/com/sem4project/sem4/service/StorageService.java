package com.sem4project.sem4.service;

import com.sem4project.sem4.entity.Photo;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    Photo uploadImage(MultipartFile file);
}
