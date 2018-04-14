package com.yangbo.springboot.springbootmq.com.yangbo.rabbitmq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.MissingNode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonUtils {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static Logger log = LoggerFactory.getLogger(JsonUtils.class);

    public static String toJsonString(Object obj) {
        try {
            if (null == obj) return null;
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("JsonUtils.toJsonString error", e);
        }
        return null;
    }

    public static <T> T parsingObject(String jsonString, Class<T> cls) {
        try {
            if (StringUtils.isBlank(jsonString) || null == cls) return null;
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return OBJECT_MAPPER.readValue(jsonString, cls);
        } catch (Exception e) {
            log.error("JsonUtils.parsingObject error", e);
        }
        return null;
    }

    public static JsonNode parsingJsonNode(String jsonString) {
        try {
            if (StringUtils.isBlank(jsonString)) {
                return MissingNode.getInstance();
            }
            return OBJECT_MAPPER.readTree(jsonString);
        } catch (Exception e) {
            log.error("JsonUtils.parsingJsonNode error", e);
        }
        return MissingNode.getInstance();
    }

    /**
     * 支持泛型
     */
    public static <T> T parsonObject(String jsonString, TypeReference<T> valueTypeRef) {
        try {
            if (StringUtils.isBlank(jsonString)) return null;
            // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
            OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return OBJECT_MAPPER.readValue(jsonString, valueTypeRef);
        } catch (Exception e) {
            log.error("JsonUtils.parsonObjectF error ", e);
        }
        return null;
    }
}