package com.study.springstudy.springSecurity;

import com.study.springstudy.springSecurity.authentication.securityContextHolder.SecurityContextHolderFilter;
import com.study.springstudy.springSecurity.securityBasic.BasicFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                )
                .addFilterBefore(new BasicFilter(), AuthorizationFilter.class)
                .addFilterBefore(new SecurityContextHolderFilter(), BasicFilter.class);


        return http.build();
    }

}