package com.DoAn.ChatCoffee.security;

import com.DoAn.ChatCoffee.service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /*@Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("filter");
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/user/register").permitAll()
                        .requestMatchers("/product/")
                        .hasAnyAuthority("USER")
                        .anyRequest()
                        .authenticated())
                .formLogin(login -> login.loginPage("/user/login").permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(handling ->handling.accessDeniedPage("/403"));

        return http.build();
    }*/
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(requests -> requests
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login()
                .loginPage("/user/login") // Chỉ định đường dẫn đến trang đăng nhập
                .defaultSuccessUrl("/"); // Chỉ định đường dẫn sau khi đăng nhập thành công

        return http.build();
    }
}