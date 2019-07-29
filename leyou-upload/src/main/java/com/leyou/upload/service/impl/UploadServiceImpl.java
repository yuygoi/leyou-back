package com.leyou.upload.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.service.api.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


/**
 * @author 叶俊晖
 * @date 2019/7/24 0024 13:40
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private ThumbImageConfig thumbImageConfig;

    private static final Logger LOGGER =  LoggerFactory.getLogger(UploadServiceImpl.class);

    private static final String IMAGE_URL = "http://image.leyou.com";

    private static final List<String> IMAGE_TYPES = Arrays.asList(
            "image/jpeg",
            "application/x-jpg",
            "application/x-img",
            "image/png",
            "image/gif",
            "image/x-icon"
            );

    @Override
    public String uploadImage(MultipartFile file) {
        //校验文件类型
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        if (!IMAGE_TYPES.contains(contentType)){
            LOGGER.info("文件类型不合法 ：{}" , filename);
            return null;
        }
        //校验文件内容
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if (read == null){
                LOGGER.info("文件内容不合法 ：{}" ,filename);
                return null;
            }
        //保存文件
            String ext = StringUtils.substringAfterLast(filename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            return IMAGE_URL + "/" + storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.info("服务器内部错误 ：{}",filename);
        }
        return null;
    }
}
