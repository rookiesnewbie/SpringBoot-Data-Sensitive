package com.desensitizer.annotations;

import cn.hutool.core.util.DesensitizedUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;

/**
 * @Author LiTeng
 * @Date 2023/11/6 20:52
 * Version 1.0
 * @Description
 */
public class SensitiveInfoSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private DesensitizedUtil.DesensitizedType desensitizedType;

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
        jsonGenerator.writeString(DesensitizedUtil.desensitized(s,desensitizedType));
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
        Sensitive annotation = beanProperty.getAnnotation(Sensitive.class);
        if (annotation != null){
           this.desensitizedType = annotation.value();
           return this;
        }
        return serializerProvider.findValueSerializer(beanProperty.getType());
    }
}
