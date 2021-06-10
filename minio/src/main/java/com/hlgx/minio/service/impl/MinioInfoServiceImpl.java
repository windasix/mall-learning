package com.hlgx.minio.service.impl;


import com.hlgx.minio.dto.MinioUploadDto;
import com.hlgx.minio.service.MinioInfoService;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author shixingjie
 * @Date 2021/6/9 11:08
 */
@Service
public class MinioInfoServiceImpl
    // extends ServiceImpl<MinioInfoMapper, MinioInfo>
    implements MinioInfoService {

    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

//    @Autowired
//    MinioInfoMapper minioInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(MinioInfoServiceImpl.class);

    @Override
//    @Async
    public MinioUploadDto uploadFile(MultipartFile file) {
        MinioUploadDto minioUploadDto = new MinioUploadDto();
        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            boolean isExist = minioClient.bucketExists(BUCKET_NAME);
            if (isExist) {
                LOGGER.info("存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(BUCKET_NAME);
                minioClient.setBucketPolicy(BUCKET_NAME, "*.*", PolicyType.READ_ONLY);
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置存储对象名称
            String objectName = sdf.format(new Date()) + "/" + filename;
            // 使用putObject上传一个文件到存储桶中
            minioClient.putObject(BUCKET_NAME, objectName, file.getInputStream(), file.getContentType());
            LOGGER.info("文件上传成功!");
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
        } catch (Exception e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return minioUploadDto;
    }

    private String getFileProgress() {
        try {
            MinioClient minioClient = new MinioClient(ENDPOINT, ACCESS_KEY, SECRET_KEY);
            InputStream name1 = minioClient.getObject(BUCKET_NAME, "name1");
            if (name1 != null) {
                // 上传失败
                return "1";
            } else {
                // 上传成功
                return "0";
            }
        } catch (Exception e) {
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return "0";
    }

    private void test() {


//
//        //fileSizeKB 总文件字节数
////计算minio分割的总文件块、minio将大文件切割为每5MB一个小文件上传
//        int total_minio_file_count = (int)Math.Ceiling(fileSizeKB / 5120);
//
//        var client = new MinioClient(
//            endpoint: "192.168.1.117:9000",
//            accessKey: "minio",
//            secretKey: "minio123456"
//                  );
//        client.SetTraceOn(new MinioRequestLog()
//        {
//            //日志记录
//            BackLogFunc = fileNo =>
//            {
//                decimal process = Math.Round((decimal)fileNo * 100 / total_minio_file_count, 2);
//                string videoProcess = $"后台上传进度：{process}%";
//                LogHelpter.AddLog(videoProcess);
//
//                //signalR推送到前端显示进度值
//                hubContext.Clients.User(loginUserId).SendAsync("ReceiveMessage", loginUserId, process);
//            }
//        });
//
//        await client.PutObjectAsync(bucketName, fileDbNew, fileSavePath);
//        stopwatch.Stop();
//
//        //上传成功后，删除本地文件
//        System.IO.File.Delete(fileSavePath);





    }


}
