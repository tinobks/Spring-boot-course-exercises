package com.develhope.tino.es13interceptor.configurations;

import com.develhope.tino.es13interceptor.controllers.LegacyController;
import com.develhope.tino.es13interceptor.interceptors.APILoggingInterceptor;
import com.develhope.tino.es13interceptor.interceptors.LegacyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfiguration implements WebMvcConfigurer {

    @Autowired
    private LegacyInterceptor legacyInterceptor;

    @Autowired
    private APILoggingInterceptor apiLoggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLoggingInterceptor);
        registry.addInterceptor(legacyInterceptor).addPathPatterns("/legacy");
    }
}
