package com.wtwlc.web_experiment.model.dto.article;


import lombok.Data;

import java.util.Date;

@Data
public class ArticleUpdateRequest {

    /**
     * id
     */
    private Integer id;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 作者id
     */
    private Integer authorId;

    /**
     * 文章内容
     */
    private String content;


}
