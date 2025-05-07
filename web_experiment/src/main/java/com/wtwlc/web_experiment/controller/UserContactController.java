package com.wtwlc.web_experiment.controller;

import com.wtwlc.web_experiment.common.BaseResponse;
import com.wtwlc.web_experiment.common.PageResult;
import com.wtwlc.web_experiment.common.ResultUtils;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactAddRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactDeleteRequest;
import com.wtwlc.web_experiment.model.dto.userContact.UserContactUpdateRequest;
import com.wtwlc.web_experiment.model.entity.UserContact;
import com.wtwlc.web_experiment.service.UserContactService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.transform.Result;

@RestController
@RequestMapping("/user_contact")
@Slf4j
public class UserContactController {
    @Autowired
    private UserContactService userContactService;

    /**
     * 查找所欲联系人
     * @return
     */
    @GetMapping("/get_all")
    @ApiOperation("获取所有联系人")
    public BaseResponse<PageResult> getAllUserContact(){

        PageResult pageResult = userContactService.getAllUserContact();

        return ResultUtils.success(pageResult);
    }

    @ApiOperation(value = "新增联系人")
    @PostMapping("/saveUserContact")
    public BaseResponse saveUserContact(@RequestBody UserContactAddRequest userContactAddRequest){

        log.info("尝试新增联系人：{}",userContactAddRequest);
        userContactService.saveUserContact(userContactAddRequest);

        return ResultUtils.success();
    }

    @ApiOperation(value = "删除联系人")
    @PostMapping("/deleteUserContact")
    public BaseResponse deleteUserContact(@RequestBody UserContactDeleteRequest userContactDeleteRequest){

        log.info("尝试删除联系人：{}",userContactDeleteRequest);
        userContactService.deleteUserContact(userContactDeleteRequest);

        return ResultUtils.success();
    }

    @ApiOperation(value = "编辑联系人")
    @PostMapping("/updateUserContact")
    public BaseResponse updateUserContact(@RequestBody UserContactUpdateRequest userContactUpdateRequest){

        log.info("尝试编辑联系人：{}",userContactUpdateRequest);
        userContactService.updateUserContact(userContactUpdateRequest);

        return ResultUtils.success();
    }



//    /**
//     * 编辑员工信息
//     * @param employeeDTO
//     * @return
//     */
//    @PutMapping
//    @ApiOperation("编辑员工信息")
//    public Result update(@RequestBody EmployeeDTO employeeDTO){
//        log.info("编辑员工信息，参数为：{}",employeeDTO);
//        employeeService.update(employeeDTO);
//
//        return Result.success();
//    }



}
