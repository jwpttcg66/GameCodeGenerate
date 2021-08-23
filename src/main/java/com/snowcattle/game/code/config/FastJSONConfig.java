package com.snowcattle.game.code.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.util.ArrayList;

@Configuration
public class FastJSONConfig {
    /**
     * Fastjson
     *
     * @return
     */
    @Bean
    public void fastJsonHttpMessageConverters() {
//        System.out.println("format");
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        List<PageAttributes.MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(PageAttributes.MediaType.APPLICATION_JSON_UTF8);
//        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect,  //禁用循环引用
//                SerializerFeature.PrettyFormat,
//                SerializerFeature.IgnoreNonFieldGetter
//        );
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        HttpMessageConverter<?> converter = fastJsonHttpMessageConverter;
//        return new HttpMessageConverters(converter);
    }
}