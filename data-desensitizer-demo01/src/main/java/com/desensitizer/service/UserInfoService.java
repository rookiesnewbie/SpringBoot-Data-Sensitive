package com.desensitizer.service;

import com.desensitizer.domian.UserInfo;
import com.desensitizer.domian.vo.UserInfoVo;

import java.util.List;

public interface UserInfoService {

    void addUser(UserInfo userInfo);

    List<UserInfoVo> listUserInfo();
}
