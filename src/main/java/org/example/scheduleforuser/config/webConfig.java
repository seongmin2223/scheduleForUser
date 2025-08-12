package org.example.scheduleforuser.config;

import org.example.scheduleforuser.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@Configurable
public class webConfig {

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<AuthFilter>();
        registrationBean.setFilter(new AuthFilter());
        registrationBean.addUrlPatterns("/schedules/*", "/users/*"); // 인증이 필요한 URL 패턴
        return registrationBean;
    }

}
