package com.wtwlc.web_experiment.model.dto.user;

import lombok.Data;

@Data
public class UserUpdatePasswordRequest {
    private String userName;

    private String userPassword;

    private String newUserPassword;

    private String confirmNewUserPassword;

}
