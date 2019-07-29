package com.leyou.upload.controller;

import com.leyou.upload.service.api.UploadService;
import org.apache.commons.lang.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 叶俊晖
 * @date 2019/7/24 0024 11:27
 */
@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("file")MultipartFile file){
        String url = uploadService.uploadImage(file);
        if (StringUtils.isBlank(url)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(url);
    }

}
