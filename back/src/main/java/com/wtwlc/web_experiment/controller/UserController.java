package com.wtwlc.web_experiment.controller;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.wtwlc.web_experiment.common.BaseResponse;
import com.wtwlc.web_experiment.common.ErrorCode;
import com.wtwlc.web_experiment.common.ResultUtils;
import com.wtwlc.web_experiment.exception.BusinessException;
import com.wtwlc.web_experiment.model.dto.user.UserLoginRequest;
import com.wtwlc.web_experiment.model.dto.user.UserRegisterRequest;
import com.wtwlc.web_experiment.model.dto.user.UserUpdatePasswordRequest;
import com.wtwlc.web_experiment.model.entity.User;
import com.wtwlc.web_experiment.model.vo.LoginUserVO;
import com.wtwlc.web_experiment.model.vo.UserArticleNumberVO;
import com.wtwlc.web_experiment.model.vo.UserVO;
import com.wtwlc.web_experiment.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> loginUser(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest){
        if(userLoginRequest == null){
            return null;
        }
        String userName = userLoginRequest.getUserName();
        String userPassword = userLoginRequest.getUserPassword();
        if(userName == null || userPassword == null ){
            return null;
        }

        LoginUserVO loginUserVO = userService.userLogin(userName, userPassword, httpServletRequest);

        System.out.println("find user loginUserVO = " + loginUserVO);

        if(loginUserVO == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"未找到匹配用户");
        }

        return ResultUtils.success(loginUserVO);
    }

    @ApiOperation("用户修改密码")
    @PostMapping("/update/password")
    public BaseResponse<Boolean>updateUserPassword(@RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest,HttpServletRequest httpServletRequest){
        if (userUpdatePasswordRequest == null){
            return null;
        }

        String userName = userUpdatePasswordRequest.getUserName();
        String userPassword = userUpdatePasswordRequest.getUserPassword();
        String newUserPassword = userUpdatePasswordRequest.getNewUserPassword();
        String confirmNewUserPassword = userUpdatePasswordRequest.getConfirmNewUserPassword();

        Boolean result = userService.updateUserPassword(userName,userPassword,newUserPassword,confirmNewUserPassword,httpServletRequest);

        return ResultUtils.success(result);
    }


    @ApiOperation("用户注册")
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        String name = userRegisterRequest.getName();
        String password = userRegisterRequest.getPassword();
        String email = userRegisterRequest.getEmail();
        Date birthday = userRegisterRequest.getBirthday();
        String avatar = userRegisterRequest.getAvatar();

        if(StringUtils.isAnyBlank(name,password,email,avatar) || birthday ==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long result = userService.userRegister(name,password,email,birthday,avatar);

        return ResultUtils.success(result);
    }


    /**
     * 根据id获取用户包装类
     * @param id
     * @return
     */
    @GetMapping("/get/vo")
    public BaseResponse<UserVO> getUserVOById(long id){
        User user = userService.getById(id);
        if(user == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return ResultUtils.success(userVO);

    }



    @GetMapping("/get_all_user_and_article_number")
    public BaseResponse<List<UserArticleNumberVO>> getAllUserAndArticleNumber(){
        List<UserArticleNumberVO> list = userService.countArticlesByAuthorWithUser();
        if(list == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(list);
    }


}
