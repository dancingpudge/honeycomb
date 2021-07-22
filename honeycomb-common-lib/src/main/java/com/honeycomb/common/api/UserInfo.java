package com.honeycomb.common.api;

/**
 * @PackgeName: com.honeycomb.common.api
 * @Description: 平台用户信息
 * @Author: LiuH
 * @Date 2021-01-10 13:09:13
 **/

import java.io.Serializable;

public class UserInfo implements Serializable {
    private Integer id;
    /**
     * 用户名  账号id
     */
    private String userName;
    /**
     * 用户真实姓名
     */
    private String userNickname;

    public static UserInfo saas() {
        UserInfo userInfo = new UserInfo();
        userInfo.id = 1;
        userInfo.userName = "saas";
        userInfo.userNickname = "saas";
        return userInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }
}
