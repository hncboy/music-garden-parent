package com.hncboy;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: hncboy
 * Date: 2018/11/27
 * Time: 16:39
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") //资源映射，可以通过/**访问所有资源
                .addResourceLocations("classpath:/META-INF/resources/") //swagger2页面
                .addResourceLocations("file:D:/Project/WxxcxProjects/MusicGarden/music-garden-user/");
    }
}
