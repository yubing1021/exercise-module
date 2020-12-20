/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.darben.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 功能说明  HTTP响应结果序列化转换类
 *
 * @author darben
 * @version 1.0.0
 * @date 2020/11/29
 */
@Configuration
public class HttpMessageConvertConfig {

    /**
     * 功能说明 HTTP响应结果序列化
     *
     * @return
     * @author 陈禹晴
     * @date 2020/10/13 17:45
     * @version 1.0.0
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 创建FastJson信息转换对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();

        List<MediaType> supportedMediaTypes = Lists.newArrayList();
        // 从1.1.41升级到1.2.之后的版本必须配置，否则会报错
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);

        // 创建FastJson对象并设定序列化规则
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        // 设置序列化功能特性
        fastJsonConfig.setSerializerFeatures(
                // 消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                // 输出值为null的字段
                SerializerFeature.WriteMapNullValue,
                // 全局修改日期格式 yyyy-MM-dd HH:mm:ss
                SerializerFeature.WriteDateUseDateFormat
        );

        // 规则赋予转换对象
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.defaultCharset());
        return new HttpMessageConverters(fastJsonHttpMessageConverter, stringHttpMessageConverter);
    }
}
