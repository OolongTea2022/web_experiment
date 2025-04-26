package com.wtwlc.web_experiment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtwlc.web_experiment.common.ErrorCode;
import com.wtwlc.web_experiment.exception.BusinessException;
import com.wtwlc.web_experiment.model.entity.User;
import com.wtwlc.web_experiment.model.vo.LoginUserVO;
import com.wtwlc.web_experiment.service.UserService;
import com.wtwlc.web_experiment.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


import java.util.Date;

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

    @Override
    public Boolean updateUserPassword(String userName, String userPassword, String newUserPassword, String confirmNewUserPassword, HttpServletRequest httpServletRequest) {
        //1.检查参数是否合法
        if(org.apache.commons.lang3.StringUtils.isAnyBlank(userName,userPassword,newUserPassword,confirmNewUserPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if(!newUserPassword.equals(confirmNewUserPassword)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"两次新密码不一样");
        }

        //TODO 密码加密

        //2。查找数据库中指定数据
        QueryWrapper<User>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",userName);
        queryWrapper.eq("password",userPassword);

        User user = this.baseMapper.selectOne(queryWrapper);
        if(user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"用户名与旧密码组合不存在");
        }

        //3.更新数据库中指定数据
        user.setPassword(newUserPassword);
        boolean result = this.updateById(user);

        if(!result){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据更新失败");
        }

        //4.返回结果
        return result;
    }

    @Override
    public long userRegister(String name, String password, String email, Date birthday, String avatar) {
        //1.对参数进行检验

        //TODO 密码类型校验（长度，字符）

        //2.开始插入
        synchronized (name.intern()){
            QueryWrapper<User>queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name",name);
            long cnt = this.baseMapper.selectCount(queryWrapper);
            if(cnt>0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户名已存在");
            }
            queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email",email);
            cnt = this.baseMapper.selectCount(queryWrapper);
            if(cnt>0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"邮箱被用于注册过");
            }

            //TODO 密码加密

            User user = new User();
            user.setName(name);
            user.setPassword(password);
            user.setEmail(email);
            user.setBirthday(birthday);
            user.setMoney(0.0);
            user.setAvatar(avatar);

            boolean saveResult = this.save(user);
            if(!saveResult){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"注册失败，数据库错误");
            }

            return user.getId();//TODO 疑问:id被赋值上去了吗？
        }
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




