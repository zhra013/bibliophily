package com.ase.application;

import com.ase.application.Filter.AuthenticationFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ASEApplication {
    public static void main(String[] args) {
        SpringApplication.run(ASEApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistration(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthenticationFilter());
        registration.addUrlPatterns("/*");
        registration.setName("authFilter");
        registration.setOrder(1);
        return registration;
    }
}
