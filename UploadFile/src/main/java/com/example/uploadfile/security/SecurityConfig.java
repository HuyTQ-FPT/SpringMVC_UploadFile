package com.example.uploadfile.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().permitAll()  // Cho phép truy cập công khai vào tất cả các URL
                .and()
                .formLogin()
                .loginPage("/login")  // Trang đăng nhập tùy chỉnh (nếu có)
                .permitAll()  // Cho phép truy cập công khai vào trang đăng nhập
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();// Cho phép truy cập công khai vào chức năng logout

        return http.build();
    }
}