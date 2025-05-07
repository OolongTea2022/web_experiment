package com.wtwlc.web_experiment.service;

import com.wtwlc.web_experiment.common.PageResult;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactAddRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactDeleteRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactUpdateRequest;
import com.wtwlc.web_experiment.model.entity.UserContact;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author lenovo
* @description 针对表【user_contact】的数据库操作Service
* @createDate 2025-04-15 17:04:51
*/
public interface UserContactService extends IService<UserContact> {
    void saveUserContact(UserContactAddRequest userContactAddRequest);
    PageResult getAllUserContact();


    void deleteUserContact(UserContactDeleteRequest userContactDeleteRequest);

    void updateUserContact(UserContactUpdateRequest userContactUpdateRequest);
}
