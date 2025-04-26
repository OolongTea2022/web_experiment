package com.wtwlc.web_experiment.service;

import com.wtwlc.web_experiment.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wtwlc.web_experiment.model.vo.LoginUserVO;
import com.wtwlc.web_experiment.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
* @author lenovo
* @description 针对表【user】的数据库操作Service
* @createDate 2025-04-15 17:04:51
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录
     * @param name
     * @param password
     * @param request
     * @return
     */
    public LoginUserVO userLogin(String name, String password, HttpServletRequest request);


//    public User getLoginUser(HttpServletRequest request);
//
//    public UserVO getUserVO(User user);

    public LoginUserVO getLoginUserVO(User user);

    /**
     * 用户更新密码
     * @param userName
     * @param userPassword
     * @param newUserPassword
     * @param confirmNewUserPassword
     * @param httpServletRequest
     * @return
     */
    Boolean updateUserPassword(String userName, String userPassword, String newUserPassword, String confirmNewUserPassword, HttpServletRequest httpServletRequest);

    /**
     * 注册用户
     * @param name
     * @param password
     * @param email
     * @param birthday
     * @param avatar
     * @return
     */
    long userRegister(String name, String password, String email, Date birthday, String avatar);
}
