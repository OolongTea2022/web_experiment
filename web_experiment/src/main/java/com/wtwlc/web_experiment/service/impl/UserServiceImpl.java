package com.wtwlc.web_experiment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtwlc.web_experiment.model.entity.User;
import com.wtwlc.web_experiment.model.vo.LoginUserVO;
import com.wtwlc.web_experiment.model.vo.UserVO;
import com.wtwlc.web_experiment.service.UserService;
import com.wtwlc.web_experiment.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.wtwlc.web_experiment.constant.UserConstant.USER_LOGIN_STATE;

/**
* @author lenovo
* @description 针对表【user】的数据库操作Service实现
* @createDate 2025-04-15 17:04:51
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Override
    public LoginUserVO userLogin(String name, String password, HttpServletRequest request) {
        // 1.校验参数是否合法
        if(StringUtils.isEmpty(name) && StringUtils.isEmpty(password)){
            return null;
        }

        // 2.加密 TODO 加密

        // 3.查询数据库是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        queryWrapper.eq("password",password);
        User user = this.baseMapper.selectOne(queryWrapper);
        //用户不存在
        if(user == null){
            log.info("user login failed, userAccount cannot match userPassword");
            return null;
        }

        // 4.记录用户登录态
        request.getSession().setAttribute("user_login",user);

        return this.getLoginUserVO(user);

    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if(user == null){
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user,loginUserVO);
        return loginUserVO;
    }

//    @Override
//    public User getLoginUser(HttpServletRequest request) {
//        //查看是否已经登录
//        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
//        User currentUser = (User) userObj;
//        if(currentUser == null || currentUser.getId() == null){
//            return null;
//        }
//
//        //查找数据库
//        long userId = currentUser.getId();
//        currentUser = this.getById(userId);
//        if(currentUser == null){
//            return null;
//        }
//
//        return currentUser;
//    }
//
//    @Override
//    public UserVO getUserVO(User user) {
//        if(user == null){
//            return null;
//        }
//
//        UserVO userVO = new UserVO();
//        BeanUtils.copyProperties(user,userVO);
//
//        return userVO;
//    }


}




