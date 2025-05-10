package com.wtwlc.web_experiment.model.dto.userContact;


import lombok.Data;

import java.util.Date;

@Data
public class UserContactUpdateRequest {

    /**
     * id
     */
    private Integer id;

    /**
     * 记录创建日期或注册日期
     */
    private Date date;

    /**
     * 联系人姓名
     */
    private String name;

    /**
     * 所在省份
     */
    private String province;

    /**
     * 所在城市
     */
    private String city;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 邮政编码
     */
    private String zip;


}
