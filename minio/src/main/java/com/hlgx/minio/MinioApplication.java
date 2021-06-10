package com.hlgx.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//@MapperScan({"com.hlgx.minio.dao"})
@EnableAsync
public class MinioApplication {

    public static void main(String[] args) {
        SpringApplication.run(MinioApplication.class, args);
    }

}
