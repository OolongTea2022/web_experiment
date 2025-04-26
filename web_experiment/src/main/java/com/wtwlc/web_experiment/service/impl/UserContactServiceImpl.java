package com.wtwlc.web_experiment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtwlc.web_experiment.model.entity.UserContact;
import com.wtwlc.web_experiment.service.UserContactService;
import com.wtwlc.web_experiment.mapper.UserContactMapper;
import org.springframework.stereotype.Service;

/**
* @author lenovo
* @description 针对表【user_contact】的数据库操作Service实现
* @createDate 2025-04-15 17:04:51
*/
@Service
public class UserContactServiceImpl extends ServiceImpl<UserContactMapper, UserContact>
    implements UserContactService{

}




