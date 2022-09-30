package com.internProject.shortly.config;

//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@Order(2)
//public class OauthSecurityConfig {
//
//    @Bean
//    SecurityFilterChain oauthSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests().antMatchers("/user/**").permitAll()
//                .antMatchers("/shorten","/url").fullyAuthenticated()
//                .and()
//                .oauth2Login();
//        return http.build();
//
//
//    }
//
//}
