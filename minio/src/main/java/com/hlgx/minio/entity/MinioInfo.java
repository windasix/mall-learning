//package com.hlgx.minio.entity;
//
//import com.baomidou.mybatisplus.annotation.IdType;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import lombok.Data;
//
//import java.util.Date;
//
///**
// * @Author shixingjie
// * @Date 2021/6/9 14:14
// */
//
//@Data
//@TableName("minio_info")
//public class MinioInfo {
//
//    @TableId(type = IdType.AUTO)
//    private Long id;
//
//    /**
//     * 学校id
//     */
//    private String schoolId;
//
//    /**
//     * 区县id
//     */
//    private String districtId;
//    /**
//     * 城市id
//     */
//    private String cityId;
//    /**
//     * 服务所在地址
//     */
//    private String endPoint;
//    /**
//     * 存储桶名称
//     */
//    private String bucketName;
//    /**
//     * 访问key
//     */
//    private String accessKey;
//
//    /**
//     * 访问密钥
//     */
//    private String secretKey;
//
//    /**
//     * 创建时间
//     */
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date createTime;
//
//
//
//}
