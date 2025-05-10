package com.wtwlc.web_experiment.model.vo;

import lombok.Data;

@Data
public class UserArticleNumberVO {
    /**
     * 用户唯一标识，自增主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 当前用户文章数量
     */
    private Integer articleCount;

}
