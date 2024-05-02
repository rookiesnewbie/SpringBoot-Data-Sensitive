package com.desensitizer.service.impl;

import com.desensitizer.domian.UserInfo;
import com.desensitizer.domian.vo.UserInfoVo;
import com.desensitizer.mapper.UserInfoMapper;
import com.desensitizer.service.UserInfoService;
import io.gitee.chemors.secure.ext.annotations.Desensitization;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author LiTeng
 * @Date 2023/11/6 17:30
 * Version 1.0
 * @Description
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    @Desensitization
    public void addUser(UserInfo userInfo) {

        userInfoMapper.addUser(userInfo);

    }

    @Override
    @Desensitization
    public List<UserInfoVo> listUserInfo() {
        List<UserInfo> userInfos = userInfoMapper.listUserInfo();
        List<UserInfoVo> userInfoVos = new ArrayList<>();
        for (UserInfo userInfo : userInfos) {
            UserInfoVo userInfoVo = new UserInfoVo();
            BeanUtils.copyProperties(userInfo,userInfoVo);
            userInfoVos.add(userInfoVo);
        }
        return userInfoVos;
    }
}
