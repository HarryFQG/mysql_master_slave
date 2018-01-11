package com.yijiupi.login.pojo;

import java.io.Serializable;

/**
 * plain ordinary Java Object
 * @author fengqigui
 * 描述：用户的数据模型
 */
public class User implements Serializable {

    private Integer userId;

    private String userName;

    private String userPassword;

    private String userSex;

    private Double userSalary;

    private Boolean userFriend;

    private String userPhoto;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userSalary=" + userSalary +
                ", userFriend=" + userFriend +
                ", userPhoto=" + userPhoto +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public Double getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(Double userSalary) {
        this.userSalary = userSalary;
    }

    public Boolean getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(Boolean userFriend) {
        this.userFriend = userFriend;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}