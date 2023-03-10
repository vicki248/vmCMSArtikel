package com.juaracoding.vmCMSArtikel.dto;/*
IntelliJ IDEA 2022.3.1 (Community Edition)
Build #IC-223.8214.52, built on December 20, 2022
@Author Vicki M a.k.a. Vicki Mantovani
Java Developer
Created on 10/03/2023 17:51
@Last Modified 10/03/2023 17:51
Version 1.1
*/

import com.juaracoding.vmCMSArtikel.utils.ConstantMessage;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ForgetPasswordDTO {
    @NotEmpty(message = ConstantMessage.ERROR_PASSWORD_IS_EMPTY)
    @NotNull(message = ConstantMessage.ERROR_PASSWORD_IS_NULL)
    @Length(message = ConstantMessage.ERROR_PASSWORD_MAX_MIN_LENGTH, max = 25,min = 8)
    private String oldPassword;

    @NotEmpty(message = ConstantMessage.ERROR_NEW_PASSWORD_IS_EMPTY)
    @NotNull(message = ConstantMessage.ERROR_NEW_PASSWORD_IS_NULL)
    @Length(message = ConstantMessage.ERROR_NEW_PASSWORD_MAX_MIN_LENGTH, max = 25,min = 8)
    private String newPassword;

    @NotEmpty(message = ConstantMessage.ERROR_CONFIRM_PASSWORD_IS_EMPTY)
    @NotNull(message = ConstantMessage.ERROR_CONFIRM_PASSWORD_IS_NULL)
    @Length(message = ConstantMessage.ERROR_CONFIRM_PASSWORD_MAX_MIN_LENGTH, max = 25,min = 8)
    private String confirmPassword;
    private String email;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
