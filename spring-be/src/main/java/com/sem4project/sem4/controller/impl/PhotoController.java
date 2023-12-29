package com.sem4project.sem4.controller.impl;
import com.sem4project.sem4.dto.response.ResponseObject;
import com.sem4project.sem4.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/photo")
@CrossOrigin
public class PhotoController{
    private final PhotoService photoService;


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Uploaded")
                                .data(photoService.upload(file))
                                .build()
                );

    }

    @RequestMapping(value = "/uploads", method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> upload(@RequestParam("file") MultipartFile[] files) {
        return ResponseEntity.status(200)
                .body(
                        ResponseObject.builder()
                                .message("Uploaded")
                                .data(photoService.upload(files))
                                .build()
                );

    }

}
