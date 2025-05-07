package com.wtwlc.web_experiment.controller;

import com.wtwlc.web_experiment.common.BaseResponse;
import com.wtwlc.web_experiment.common.ResultUtils;
import com.wtwlc.web_experiment.model.dto.article.ArticleAddRequest;
import com.wtwlc.web_experiment.model.dto.article.ArticleDeleteRequest;
import com.wtwlc.web_experiment.model.dto.article.ArticleUpdateRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactAddRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactDeleteRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactUpdateRequest;
import com.wtwlc.web_experiment.model.entity.Article;
import com.wtwlc.web_experiment.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "根据id获取作者所有文章信息")
    @GetMapping("/get_articles_by_id")
    public BaseResponse<List<Article>> getArticlesById(@RequestParam("id") Long id){
        List<Article> articleList = articleService.getArticlesById(id);
        return ResultUtils.success(articleList);
    }


    @ApiOperation(value = "新增文章")
    @PostMapping("/saveArticle")
    public BaseResponse saveArticle(@RequestBody ArticleAddRequest articleAddRequest){

        log.info("尝试新增文章：{}",articleAddRequest);
        articleService.saveArticle(articleAddRequest);

        return ResultUtils.success();
    }

    @ApiOperation(value = "删除文章")
    @PostMapping("/deleteArticle")
    public BaseResponse deleteArticle(@RequestBody ArticleDeleteRequest articleDeleteRequest){

        log.info("尝试删除文章：{}",articleDeleteRequest);
        articleService.deleteArticle(articleDeleteRequest);

        return ResultUtils.success();
    }

    @ApiOperation(value = "编辑文章")
    @PostMapping("/updateArticle")
    public BaseResponse updateArticle(@RequestBody ArticleUpdateRequest articleUpdateRequest){

        log.info("尝试编辑文章：{}",articleUpdateRequest);
        articleService.updateArticle(articleUpdateRequest);

        return ResultUtils.success();
    }


}
