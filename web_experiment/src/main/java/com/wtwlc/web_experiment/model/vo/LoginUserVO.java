package com.wtwlc.web_experiment.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LoginUserVO implements Serializable {
    /**
     * 用户唯一标识，自增主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;


    /**
     * 用户邮箱地址
     */
    private String email;

    /**
     * 用户出生日期
     */
    private Date birthday;

    /**
     * 用户账户余额
     */
    private Double money;

    /**
     * 用户头像地址或文件名
     */
    private String avatar;

    private static final long serialVersionUID = 1L;
}
