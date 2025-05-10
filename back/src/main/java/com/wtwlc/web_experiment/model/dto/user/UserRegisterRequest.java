package com.wtwlc.web_experiment.model.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserRegisterRequest {

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户密码（建议加密存储）
     */
    private String password;

    /**
     * 用户邮箱地址
     */
    private String email;

    /**
     * 用户出生日期
     */
    private Date birthday;


    /**
     * 用户头像地址
     */
    private String avatar;


}
