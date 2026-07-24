package com.innorun.argumentresolver.config;

import com.innorun.argumentresolver.resolver.CustomPathVariableArgumentResolver;
import com.innorun.argumentresolver.resolver.CustomRequestParamArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new CustomRequestParamArgumentResolver());
        resolvers.add(new CustomPathVariableArgumentResolver());
    }
}