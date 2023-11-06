package com.desensitizer.domian;


import io.gitee.chemors.secure.ext.annotations.DesensitizationProp;
import io.gitee.chemors.secure.ext.enums.SensitiveTypeEnum;
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

//    @DesensitizationProp(SensitiveTypeEnum.CHINESE_NAME)
    @DesensitizationProp(value = SensitiveTypeEnum.CUSTOM,preLength = 1,sufLength = 2)  // 自定义脱敏长度
    private String name;

    private Integer age;

    @DesensitizationProp(SensitiveTypeEnum.MOBILE_PHONE)
    private String phone;

    @DesensitizationProp(SensitiveTypeEnum.ID_CARD)
    private String idCard;


    @DesensitizationProp(SensitiveTypeEnum.BANK_CARD)
    private String bankCard;

    @DesensitizationProp(SensitiveTypeEnum.PASSWORD)
    private String password;

    @DesensitizationProp(SensitiveTypeEnum.EMAIL)
    private String email;
}
