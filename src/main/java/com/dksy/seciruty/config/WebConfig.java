package com.dksy.seciruty.config;

import com.dksy.seciruty.filter.TimeFilter;
import com.dksy.seciruty.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

//@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private  TimeInterceptor timeInterceptor;

    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        TimeFilter timeFilter = new TimeFilter();
        filterFilterRegistrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        filterFilterRegistrationBean.setUrlPatterns(urls);
        return filterFilterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }
}
