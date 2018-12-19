package com.dimple.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author : Dimple
 * @version : 1.0
 * @class : WebMvcConfig
 * @description : web配置文件
 * @date : 12/19/18 10:43
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置路径映射:访问/images/test.jpg会到D:/rentHouse去查找test.jpg
        registry.addResourceHandler("/images/**").addResourceLocations("file:" + "D:/rentHouse/");

    }
}
