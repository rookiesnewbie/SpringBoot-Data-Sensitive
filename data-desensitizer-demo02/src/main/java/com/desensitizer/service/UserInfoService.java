package com.desensitizer.service;

import com.desensitizer.domian.UserInfo;

import java.util.List;

public interface UserInfoService {

    void addUser(UserInfo userInfo);

    List<UserInfo> listUserInfo();
}
