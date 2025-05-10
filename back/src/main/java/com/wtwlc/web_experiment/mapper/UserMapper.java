package com.wtwlc.web_experiment.mapper;

import com.wtwlc.web_experiment.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wtwlc.web_experiment.model.vo.UserArticleNumberVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author lenovo
* @description 针对表【user】的数据库操作Mapper
* @createDate 2025-04-15 17:04:51
* @Entity com.wtwlc.web_experiment.model.entity.User
*/
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT u.id, u.name, COUNT(a.id) as articleCount " +
            "FROM user u " +
            "LEFT JOIN article a ON u.id = a.author_id " +
            "GROUP BY u.id")
    List<UserArticleNumberVO> countArticlesByAuthorWithUser();
}




