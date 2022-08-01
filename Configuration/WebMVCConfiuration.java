    package com.xrest.spring.Configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableAutoConfiguration
public class WebMVCConfiuration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/data/**").
                addResourceLocations("file:///C://data/")
                .setCachePeriod(3600)
                .resourceChain(true).addResolver(new PathResourceResolver());
//        registry
//                .addResourceHandler("/resources/**")
//                .addResourceLocations("/resources/");
//
//        registry
//                .addResourceHandler("/resources/static/**")
//                .addResourceLocations("/resources/");
//
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }
}
