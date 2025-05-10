package com.wtwlc.web_experiment.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

    private String userName;

    private String userPassword;

}
