package com.cc.config;

import com.cc.exception.BlogException;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.FileNotFoundException;

/**
 * @author : cc
 * @date : 2019-03-15  13:08
 */
@Configuration
public class ImageMappingConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String path = null;
        try {
            path = ResourceUtils.getURL("").getPath();
            System.out.println(path);
        } catch (FileNotFoundException e) {
            throw new BlogException("ImageMappingConfig file not found exception");
        }
        // 将/myImg/** 替换成 path/**    访问/myImg/img/a.jpg时 访问磁盘上的 path/img/a.jpg
        registry.addResourceHandler("/myImg/**").addResourceLocations("file:" + path);
    }
}
