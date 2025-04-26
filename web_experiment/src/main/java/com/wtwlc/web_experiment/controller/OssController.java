package com.wtwlc.web_experiment.controller;

import com.wtwlc.web_experiment.common.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/oss")
public class OssController {
    @Autowired
    private OssUtils ossUtils;

    /**
     * 获取头像上传凭证
     * @param fileName 文件名
     * @return 上传凭证（预签名URL）
     */
    @GetMapping("/avatar/upload-credential")
    public String getAvatarUploadCredential(@RequestParam String fileName) {
        // 头像通常放在avatar目录下，以用户ID或时间戳命名
        // 使用UUID代替时间戳，避免时间推测
        String objectName = "avatar/" + UUID.randomUUID() + "-" + fileName;
//        String objectName = "avatar/" + System.currentTimeMillis() + "-" + fileName;
        // 生成10分钟后过期的上传URL
        return ossUtils.generateUploadCredential(objectName, 10);
    }
}
