package com.sem4project.sem4.service;

import com.sem4project.sem4.dto.dtomodel.PhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface PhotoService {
    PhotoDto upload(MultipartFile file);
    List<PhotoDto> upload(MultipartFile[] files);
    List<PhotoDto> getAllPhoto();
    void deletePhoto(Long id);
    PhotoDto getPhoto(UUID id);
}
