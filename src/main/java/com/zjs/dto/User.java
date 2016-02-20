package com.zjs.dto;

import java.io.Serializable;

/**
 * Created by MrDu on 16/2/20.
 */
public class User implements Serializable{
    private String userCode;
    private String userName;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
