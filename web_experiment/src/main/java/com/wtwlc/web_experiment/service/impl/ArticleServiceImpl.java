package com.wtwlc.web_experiment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtwlc.web_experiment.common.ErrorCode;
import com.wtwlc.web_experiment.exception.BusinessException;
import com.wtwlc.web_experiment.model.dto.article.ArticleAddRequest;
import com.wtwlc.web_experiment.model.dto.article.ArticleDeleteRequest;
import com.wtwlc.web_experiment.model.dto.article.ArticleUpdateRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactUpdateRequest;
import com.wtwlc.web_experiment.model.entity.Article;
import com.wtwlc.web_experiment.model.entity.UserContact;
import com.wtwlc.web_experiment.model.vo.UserArticleNumberVO;
import com.wtwlc.web_experiment.service.ArticleService;
import com.wtwlc.web_experiment.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;

/**
* @author lenovo
* @description 针对表【article】的数据库操作Service实现
* @createDate 2025-05-07 20:31:20
*/
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> getArticlesById(Long id) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("author_id",id);
        return this.list(queryWrapper);
    }

    @Override
    public void saveArticle(ArticleAddRequest articleAddRequest) {
        Article article = new Article();
        BeanUtils.copyProperties(articleAddRequest,article);

        int insert = articleMapper.insert(article);
        if(insert > 0){
            log.info("article add success,article = {}",article);
        }else{
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"article add failed");
        }

    }

    @Override
    public void deleteArticle(ArticleDeleteRequest articleDeleteRequest) {
        if(articleDeleteRequest == null || articleDeleteRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean remove = this.removeById(articleDeleteRequest.getId());
        if(!remove){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"文章删除失败");
        }
    }

    @Override
    public void updateArticle(ArticleUpdateRequest articleUpdateRequest) {
        if (articleUpdateRequest == null || articleUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        Article existArticle = this.getById(articleUpdateRequest.getId());

        if (existArticle == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "联系人不存在");
        }

        Article article = new Article();
        BeanUtils.copyProperties(articleUpdateRequest,article);
        int result = articleMapper.updateById(article);

        if(result > 0){
            log.info("article update success,article = {}",article);
        }else{
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"文章更新失败");
        }

    }


}




