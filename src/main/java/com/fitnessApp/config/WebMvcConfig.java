package com.fitnessApp.config;

import com.fitnessApp.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Register the interceptor and specify which URL patterns to apply it to.
        registry.addInterceptor(new SessionInterceptor())
                .addPathPatterns("/fitness/**") // Apply to all paths under /fitness
                .excludePathPatterns("/fitness/loginform", "/fitness/registerUsers", "/fitness/showRegistrationPage"); // Exclude login/registration pages
    }
}
