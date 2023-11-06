package com.desensitizer.domian;


import cn.hutool.core.util.DesensitizedUtil;
import com.desensitizer.annotations.Sensitive;
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
//    @DesensitizationProp(value = SensitiveTypeEnum.CUSTOM,preLength = 1,sufLength = 2)  // 自定义脱敏长度
    @Sensitive(DesensitizedUtil.DesensitizedType.CHINESE_NAME)
    private String name;

    private Integer age;

//    @DesensitizationProp(SensitiveTypeEnum.MOBILE_PHONE)
    @Sensitive(DesensitizedUtil.DesensitizedType.MOBILE_PHONE)
    private String phone;

//    @DesensitizationProp(SensitiveTypeEnum.ID_CARD)
    @Sensitive(DesensitizedUtil.DesensitizedType.ID_CARD)
    private String idCard;


//    @DesensitizationProp(SensitiveTypeEnum.BANK_CARD)
    @Sensitive(DesensitizedUtil.DesensitizedType.BANK_CARD)
    private String bankCard;

//    @DesensitizationProp(SensitiveTypeEnum.PASSWORD)
    @Sensitive(DesensitizedUtil.DesensitizedType.PASSWORD)
    private String password;

//    @DesensitizationProp(SensitiveTypeEnum.EMAIL)
    @Sensitive(DesensitizedUtil.DesensitizedType.EMAIL)
    private String email;
}
