package com.haole.logistics.r2dbc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.haole.logistics.r2dbc.common.JSONParseException;
import com.haole.logistics.r2dbc.common.JSONSerializeException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public interface JSON {

    ObjectMapper objectMapper = (new ObjectMapper())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            .registerModule(new ParameterNamesModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setTimeZone(TimeZone.getTimeZone("GMT+8"));

    static <T> T parseObject(String text, Class<T> clazz) {
        if (!StringUtils.hasText(text)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(text, clazz);
            } catch (Exception e) {
                throw new JSONParseException(String.format("JSON String : %s deserialize [%s]异常", text, clazz.getName()), e);
            }
        }
    }

    static <T> T parseObject(String text, TypeReference<T> valueTypeRef) {
        if (!StringUtils.hasText(text)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(text, valueTypeRef);
            } catch (Exception e) {
                throw new JSONParseException(String.format("JSON String : %s deserialize [%s]异常", text, valueTypeRef.getType().getTypeName()), e);
            }
        }
    }

    static <T> List<T> parseArray(String text, Class<T> clazz) {
        if (!StringUtils.hasText(text)) {
            return null;
        } else {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, new Class[]{clazz});

            try {
                return (List) objectMapper.readValue(text, javaType);
            } catch (Exception e) {
                throw new JSONParseException(String.format("JSON String : %s deserialize [%s]异常", text, clazz.getName()), e);
            }
        }
    }

    static String toJSONString(Object obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new JSONSerializeException("JSON 序列化异常", e);
            }
        }
    }
}
