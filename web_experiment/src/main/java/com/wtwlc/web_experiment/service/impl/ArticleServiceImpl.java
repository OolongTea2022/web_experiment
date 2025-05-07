package com.wtwlc.web_experiment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtwlc.web_experiment.model.entity.Article;
import com.wtwlc.web_experiment.service.ArticleService;
import com.wtwlc.web_experiment.mapper.ArticleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【article】的数据库操作Service实现
* @createDate 2025-05-07 20:31:20
*/
@Service
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




