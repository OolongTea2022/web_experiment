package com.wtwlc.web_experiment.controller;

import com.wtwlc.web_experiment.common.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/oss")
@Slf4j
public class OssController {
    @Autowired
    private OssUtils ossUtils;


    /**
     * 获取oss上传签名
     * @param fileName
     * @param contentType
     * @return
     */
    @GetMapping("/avatar/upload-credential")
    public String getAvatarUploadCredential(
            @RequestParam String fileName,
            @RequestParam String contentType) {
        try {
            String objectName = "avatar/" + UUID.randomUUID() + "-" + fileName;
            return ossUtils.generateUploadCredential(objectName, contentType, 10);
        } catch (Exception e) {
            e.printStackTrace(); // 打印堆栈信息
            throw new RuntimeException("生成上传凭证失败: " + e.getMessage());
        }
    }
/*    *
     * 获取头像上传凭证
     * @param fileName 文件名
     * @return 上传凭证（预签名URL）

    @GetMapping("/avatar/upload-credential")
    public String getAvatarUploadCredential(@RequestParam String fileName) {

        // 头像通常放在avatar目录下，以用户ID或时间戳命名
        // 使用UUID代替时间戳，避免时间推测
        String objectName = "avatar/" + UUID.randomUUID() + "-" + fileName;
//        String objectName = "avatar/" + System.currentTimeMillis() + "-" + fileName;
        // 生成10分钟后过期的上传URL
        log.info(objectName);
        String url = ossUtils.generateUploadCredential(objectName, 10);
        log.info(url);

        return url;
    }*/
}
