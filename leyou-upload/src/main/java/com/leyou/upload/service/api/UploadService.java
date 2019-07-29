package com.leyou.upload.service.api;

import org.springframework.web.multipart.MultipartFile; /**
 * @author 叶俊晖
 * @date 2019/7/24 0024 13:40
 */
public interface UploadService {
    String uploadImage(MultipartFile file);
}
