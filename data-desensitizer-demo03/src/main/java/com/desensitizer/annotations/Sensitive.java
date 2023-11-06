package com.desensitizer.annotations;

import cn.hutool.core.util.DesensitizedUtil;
import com.desensitizer.enums.DesensitizationTypeEnum;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveInfoSerialize.class)
public @interface Sensitive {
    DesensitizationTypeEnum type() default DesensitizationTypeEnum.CUSTOM;

    /**
     * 开始脱敏的位置（包含）
     * @return
     */
    int start() default 0;

    /**
     * 脱敏的结束位置（不包含）
     * @return
     */
    int end() default 0;
}
