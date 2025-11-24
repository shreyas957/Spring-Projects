package com.shreyas.config;

import com.shreyas.filter.MyCustomFilter;
import com.shreyas.interceptor.MyCustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private MyCustomInterceptor myCustomInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myCustomInterceptor)
                .addPathPatterns("/api/**")
                .excludePathPatterns("/api/login");


        // Order is maintained by addInterceptor method on registry.
        // 1st added will be executed 1st.
    }


    @Bean
    public FilterRegistrationBean<MyCustomFilter> filterRegistrationBean() {
        FilterRegistrationBean<MyCustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyCustomFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
