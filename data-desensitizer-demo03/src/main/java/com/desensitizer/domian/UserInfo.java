package com.desensitizer.domian;


import com.desensitizer.annotations.Sensitive;
import com.desensitizer.enums.DesensitizationTypeEnum;
import lombok.Data;

/**
 * @Author LiTeng
 * @Date 2023/11/6 17:20
 * Version 1.0
 * @Description
 */

@Data
public class UserInfo {
    private Integer id;

    @Sensitive(type = DesensitizationTypeEnum.CUSTOM,start = 1,end = 2)
    private String name;

    private Integer age;

    @Sensitive(type = DesensitizationTypeEnum.MOBILE_PHONE)
    private String phone;

    @Sensitive(type = DesensitizationTypeEnum.ID_CARD)
    private String idCard;


    @Sensitive(type = DesensitizationTypeEnum.BANK_CARD)
    private String bankCard;

    @Sensitive(type = DesensitizationTypeEnum.PASSWORD)
    private String password;

    @Sensitive(type = DesensitizationTypeEnum.EMAIL)
    private String email;

    @Sensitive(type = DesensitizationTypeEnum.CUSTOM,start = 3,end = 12)
    private String addrss = "上海市浦东新区新川路508号";
}
