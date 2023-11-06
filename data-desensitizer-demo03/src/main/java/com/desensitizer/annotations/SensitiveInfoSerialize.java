package com.desensitizer.annotations;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.util.DesensitizedUtil;
import com.desensitizer.enums.DesensitizationTypeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Objects;

/**
 * @Author LiTeng
 * @Date 2023/11/6 20:52
 * Version 1.0
 * @Description
 */
@AllArgsConstructor
@NoArgsConstructor
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizationTypeEnum desensitizedType;

    private Integer start;

    private Integer end;

    /**
     *
     * @param s Value to serialize; can <b>not</b> be null.
     * @param jsonGenerator Generator used to output resulting Json content
     * @param serializerProvider Provider that can be used to get serializers for
     *   serializing Objects value contains, if any.
     * @throws IOException
     */
    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        switch (desensitizedType){
            // 名称
            case CHINESE_NAME:
                jsonGenerator.writeString(DesensitizedUtil.chineseName(s));
                break;
            case ID_CARD:  // 身份证号码
                jsonGenerator.writeString(DesensitizedUtil.idCardNum(s,start,2));
                break;
            case FIXED_PHONE:  // 座机号码
                jsonGenerator.writeString(DesensitizedUtil.fixedPhone(s));
                break;
            case MOBILE_PHONE: // 手机号码
                jsonGenerator.writeString(DesensitizedUtil.mobilePhone(s));
                break;
            case ADDRESS:  // 住址
                jsonGenerator.writeString(DesensitizedUtil.address(s,8));
                break;
            case EMAIL:  // 邮箱
                jsonGenerator.writeString(DesensitizedUtil.email(s));
                break;
            case BANK_CARD:  // 银行卡号
                jsonGenerator.writeString(DesensitizedUtil.bankCard(s));
                break;
            case PASSWORD:  // 密码
                jsonGenerator.writeString(DesensitizedUtil.password(s));
                break;
            // 自定义
            case CUSTOM:
                jsonGenerator.writeString(CharSequenceUtil.hide(s,start,end));
                break;

        }
    }

    /**
     *
     * @param serializerProvider Serializer provider to use for accessing config, other serializers
     * @param beanProperty Method or field that represents the property
     *   (and is used to access value to serialize).
     *   Should be available; but there may be cases where caller cannot provide it and
     *   null is passed instead (in which case impls usually pass 'this' serializer as is)
     *
     * @return
     * @throws JsonMappingException
     */
    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        if (beanProperty != null) {
            // 判断数据类型是否为String类型
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                // 获取定义的注解
                Sensitive sensitive = beanProperty.getAnnotation(Sensitive.class);
                // 为null
                if (sensitive == null) {
                    sensitive = beanProperty.getContextAnnotation(Sensitive.class);
                }
                // 不为null
                if (sensitive != null) {
                    // 创建定义的序列化类的实例并且返回，入参为注解定义的type,开始位置，结束位置。
                    return new SensitiveInfoSerialize(sensitive.type(), sensitive.start(),
                            sensitive.end());
                }
            }

            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(null);
    }
}
