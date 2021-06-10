package com.hlgx.minio.controller;


import com.hlgx.minio.common.CommonResult;
import com.hlgx.minio.dto.MinioUploadDto;
import com.hlgx.minio.service.MinioInfoService;
import io.minio.MinioClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;

/**
 * Created by macro on 2019/12/25.
 */
@Api(tags = "MinioController", description = "MinIO对象存储管理")
@Controller
@RequestMapping("/minio")
public class MinioController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @Autowired
    MinioInfoService minioInfoService;

    @ApiOperation("文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("file") MultipartFile file) {
        try {
            MinioUploadDto minioUploadDto = minioInfoService.uploadFile(file);
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }

    @ApiOperation("文件删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            minioClient.removeObject(BUCKET_NAME, objectName);
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }

    @ApiOperation("获取文件状态")
    @RequestMapping(value = "/file/status", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getFileStatus(@RequestParam("objectName") String objectName) {
        String status = "0";
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            InputStream name1 = minioClient.getObject(BUCKET_NAME, objectName);
            if (name1 != null) {
                // 上传成功
                status = "1";
            }
        } catch (Exception e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return CommonResult.success(status);
    }

    @ApiOperation("文件下载")
    @RequestMapping(value = "/download", method = RequestMethod.POST)
    @ResponseBody
    public void download(@RequestParam("objectName") String objectName, HttpServletResponse response) {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            InputStream stream = minioClient.getObject(BUCKET_NAME, objectName);
            ServletOutputStream output = response.getOutputStream();
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(stream, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
