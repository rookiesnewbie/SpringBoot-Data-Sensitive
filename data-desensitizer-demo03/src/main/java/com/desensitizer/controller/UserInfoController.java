package com.desensitizer.controller;

import com.desensitizer.domian.UserInfo;
import com.desensitizer.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author LiTeng
 * @Date 2023/11/6 17:30
 * Version 1.0
 * @Description
 */

@RestController
@RequestMapping("/user")
@Slf4j
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/add")
    public String addUser(@RequestBody UserInfo userInfo){
        userInfoService.addUser(userInfo);
        return "添加成功";
    }

//    @Desensitization
    @GetMapping("/list")
    public List<UserInfo> userInfoList(){
        return userInfoService.listUserInfo();
    }

}
