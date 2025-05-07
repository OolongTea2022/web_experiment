package com.wtwlc.web_experiment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wtwlc.web_experiment.common.ErrorCode;
import com.wtwlc.web_experiment.common.PageResult;
import com.wtwlc.web_experiment.exception.BusinessException;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactAddRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactDeleteRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactUpdateRequest;
import com.wtwlc.web_experiment.model.entity.UserContact;
import com.wtwlc.web_experiment.service.UserContactService;
import com.wtwlc.web_experiment.mapper.UserContactMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

/**
* @author lenovo
* @description 针对表【user_contact】的数据库操作Service实现
* @createDate 2025-04-15 17:04:51
*/
@Service
@Slf4j
public class UserContactServiceImpl extends ServiceImpl<UserContactMapper, UserContact>
    implements UserContactService{

    @Autowired
    UserContactMapper userContactMapper;


    @Override
    public void saveUserContact(UserContactAddRequest userContactAddRequest) {

        UserContact userContact = new UserContact();

        //对象属性拷贝
        BeanUtils.copyProperties(userContactAddRequest,userContact);

        userContactMapper.insert(userContact);
        //TODO 直接返回void
    }

    @Override
    public PageResult getAllUserContact() {
        //查询所有人，TODO 前后端改成分页查询
        List<UserContact> userContacts = userContactMapper.selectList(null);
        long total = userContacts.size();
        return new PageResult(total,userContacts);
    }

    @Override
    public void deleteUserContact(UserContactDeleteRequest userContactDeleteRequest) {
        if(userContactDeleteRequest == null || userContactDeleteRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        boolean b = this.removeById(userContactDeleteRequest.getId());
        if(!b){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"删除失败");
        }
    }

    @Override
    public void updateUserContact(UserContactUpdateRequest userContactUpdateRequest) {
        if (userContactUpdateRequest == null || userContactUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 先查询记录是否存在
        UserContact existingContact = this.getById(userContactUpdateRequest.getId());
        if (existingContact == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "联系人不存在");
        }

        UserContact userContact = new UserContact();
        BeanUtils.copyProperties(userContactUpdateRequest, userContact);

        boolean result = this.updateById(userContact);

        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新失败");
        }
    }

}




