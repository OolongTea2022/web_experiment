package com.wtwlc.web_experiment.controller;


import com.wtwlc.web_experiment.common.BaseResponse;
import com.wtwlc.web_experiment.common.ErrorCode;
import com.wtwlc.web_experiment.common.ResultUtils;
import com.wtwlc.web_experiment.model.dto.user.UserLoginRequest;
import com.wtwlc.web_experiment.model.vo.LoginUserVO;
import com.wtwlc.web_experiment.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest httpServletRequest){
        if(userLoginRequest == null){
            return null;
        }
        String userName = userLoginRequest.getUserName();
        String userPassword = userLoginRequest.getUserPassword();
        if(userName == null || userPassword == null ){
            return null;
        }

        LoginUserVO loginUserVO = userService.userLogin(userName, userPassword, httpServletRequest);
        return ResultUtils.success(loginUserVO);

    }
}
