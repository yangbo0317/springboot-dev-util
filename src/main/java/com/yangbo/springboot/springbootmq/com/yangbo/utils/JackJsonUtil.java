package com.yangbo.springboot.springbootmq.com.yangbo.utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @Type JackJsonUtil
 * @Desc jackjson的对象转换
 * @author yangbo
 * @date 2017-09-19
 * @Version V1.0
 */
@Component
public class JackJsonUtil {

    private static final Log LOGGER = LogFactory.getLog(JackJsonUtil.class);

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper = new ObjectMapper();
    }

    /**
     * 对象转成json
     * 
     * @param obj
     * @return
     */
    public String writeString(Object obj) {
        try {
            if (obj == null) {
                return "";
            }
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            LOGGER.error("JackJsonUtil writeJson error ", e);
            return null;
        }
    }

    /**
     * json 转回对象
     * 
     * @param json
     * @param valueType
     * @return
     */
    public <T> T read(String json, Class<T> valueType) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return objectMapper.readValue(json, valueType);
        } catch (Exception e) {
            LOGGER.error("json=" + json, e);
            return null;
        }
    }

    /**
     * json字符串转化为list
     * 
     * 还可以 直接使用 JsonUtils.getInstance().readValue(String content, new
     * TypeReference<List<T>>(){})方式
     * 
     * @param <T>
     * @param content
     * @return
     * @throws IOException
     */
    public <T> List<T> toJavaBeanList(String content, TypeReference<List<T>> typeReference) throws IOException {
        try {
            return objectMapper.readValue(content, typeReference);
        } catch (JsonParseException e) {
            LOGGER.error("json字符串转化为 list失败,原因:", e);
            throw new RuntimeException("json字符串转化为 list失败");
        } catch (JsonMappingException e) {
            LOGGER.error("json字符串转化为 list失败,原因", e);
            throw new JsonMappingException("json字符串转化为 list失败");
        } catch (IOException e) {
            LOGGER.error("json字符串转化为 list失败,原因", e);
            throw new IOException("json字符串转化为 list失败");
        }
    }
}
