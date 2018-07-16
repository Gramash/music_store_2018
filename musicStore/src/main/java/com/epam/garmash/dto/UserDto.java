package com.epam.garmash.dto;

import com.epam.garmash.beans.User;
import com.epam.garmash.web.ViewConstants;

import javax.servlet.http.HttpServletRequest;

public class UserDto {

    private String name;
    private String lastName;
    private String email;
    private String avatar;
    private String pass;
    private String passCheck;

    public UserDto(String name, String lastName, String email, String pass, String passCheck) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.pass = pass;
        this.passCheck = passCheck;
    }

    public UserDto(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public static UserDto createSignUpForm(HttpServletRequest req) {
        return new UserDto(req.getParameter(ViewConstants.NAME),
                req.getParameter(ViewConstants.LASE_NAME),
                req.getParameter(ViewConstants.EMAIL),
                req.getParameter(ViewConstants.PASSWORD),
                req.getParameter(ViewConstants.PASSWORD_CHECK));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPassCheck() {
        return passCheck;
    }

    public void setPassCheck(String passCheck) {
        this.passCheck = passCheck;
    }

    public User createUser() {
        return new User(name, lastName, email, pass, avatar, false);
    }

}