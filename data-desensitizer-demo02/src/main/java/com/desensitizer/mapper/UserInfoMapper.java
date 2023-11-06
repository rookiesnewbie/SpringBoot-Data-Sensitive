package com.desensitizer.mapper;

import com.desensitizer.domian.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {

    void addUser(UserInfo userInfo);

    List<UserInfo> listUserInfo();
}
