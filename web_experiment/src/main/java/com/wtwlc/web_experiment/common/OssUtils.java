package com.wtwlc.web_experiment.common;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.wtwlc.web_experiment.config.OssConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;

@Component
public class OssUtils {

    @Autowired
    private OssConfig ossConfig;

    public String generateUploadCredential(String objectName, String contentType, long expireMinutes) {
        // 创建OSS客户端（建议用try-with-resources自动关闭）

        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret());
        // 1. 创建预签名请求对象
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(
                ossConfig.getBucketName(),
                objectName,
                HttpMethod.PUT);

        // 2. 关键设置：必须与前端上传时的Content-Type一致
        request.setContentType(contentType);
        System.out.println(contentType);//TODO 删除
        // 3. 防止文件覆盖
        request.addHeader("x-oss-forbid-overwrite", "true");

        // 4. 设置过期时间
        request.setExpiration(new Date(
                System.currentTimeMillis() + expireMinutes * 60 * 1000));

        // 5. 生成带签名的URL
        return ossClient.generatePresignedUrl(request).toString();
    }

//    /**
//     * 生成上传凭证（实际上阿里云OSS不需要预签名PUT上传，这里返回预签名URL）
//     * @param objectName 对象名称（包含路径）
//     * @param expireTime 过期时间（分钟）
//     * @return 预签名URL
//     */
//    public String generateUploadCredential(String objectName, long expireTime) {
//        // 设置URL过期时间
//        Date expiration = new Date(System.currentTimeMillis() + expireTime * 60 * 1000);
//
//        // 生成OSS客户端
//        OSS ossClient = new OSSClientBuilder().build(
//                ossConfig.getEndpoint(),
//                ossConfig.getAccessKeyId(),
//                ossConfig.getAccessKeySecret());
//
//        // 生成预签名URL（PUT方法）
//        URL signedUrl = ossClient.generatePresignedUrl(
//                ossConfig.getBucketName(),
//                objectName,
//                expiration);
//
//        // 关闭OSSClient
//        ossClient.shutdown();
//
//        return signedUrl.toString();
//    }

    /**
     * 上传文件
     */
    public String uploadFile(InputStream inputStream, String objectName) {
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret());

        ossClient.putObject(
                ossConfig.getBucketName(),
                objectName,
                inputStream);

        ossClient.shutdown();

        return getFileUrl(objectName);
    }

    /**
     * 获取文件访问URL
     */
    public String getFileUrl(String objectName) {
        return "https://" + ossConfig.getBucketName() + "." +
                ossConfig.getEndpoint().replace("https://", "") + "/" + objectName;
    }
}