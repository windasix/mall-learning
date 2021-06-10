package com.hlgx.minio.service;

import com.hlgx.minio.dto.MinioUploadDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author shixingjie
 * @Date 2021/6/9 11:07
 */
public interface MinioInfoService
    // extends IService<MinioInfo>
{

    MinioUploadDto uploadFile(MultipartFile file);

}
