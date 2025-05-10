package com.wtwlc.web_experiment.model.dto.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ArticleAddRequest {
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
