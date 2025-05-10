package com.wtwlc.web_experiment.service;

import com.wtwlc.web_experiment.model.dto.article.ArticleAddRequest;
import com.wtwlc.web_experiment.model.dto.article.ArticleDeleteRequest;
import com.wtwlc.web_experiment.model.dto.article.ArticleUpdateRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactUpdateRequest;
import com.wtwlc.web_experiment.model.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wtwlc.web_experiment.model.vo.UserArticleNumberVO;

import java.util.List;

/**
* @author lenovo
* @description 针对表【article】的数据库操作Service
* @createDate 2025-05-07 20:31:20
*/
public interface ArticleService extends IService<Article> {

    List<Article> getArticlesById(Long id);

    void saveArticle(ArticleAddRequest articleAddRequest);

    void deleteArticle(ArticleDeleteRequest articleDeleteRequest);


    void updateArticle(ArticleUpdateRequest articleUpdateRequest);
}
