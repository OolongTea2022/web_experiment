package com.wtwlc.web_experiment.common;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
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

    /**
     * 生成上传凭证（实际上阿里云OSS不需要预签名PUT上传，这里返回预签名URL）
     * @param objectName 对象名称（包含路径）
     * @param expireTime 过期时间（分钟）
     * @return 预签名URL
     */
    public String generateUploadCredential(String objectName, long expireTime) {
        // 设置URL过期时间
        Date expiration = new Date(System.currentTimeMillis() + expireTime * 60 * 1000);

        // 生成OSS客户端
        OSS ossClient = new OSSClientBuilder().build(
                ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(),
                ossConfig.getAccessKeySecret());

        // 生成预签名URL（PUT方法）
        URL signedUrl = ossClient.generatePresignedUrl(
                ossConfig.getBucketName(),
                objectName,
                expiration);

        // 关闭OSSClient
        ossClient.shutdown();

        return signedUrl.toString();
    }

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