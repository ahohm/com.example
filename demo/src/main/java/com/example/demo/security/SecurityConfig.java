package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
// @EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // http
    // .authorizeHttpRequests(authorize -> authorize
    // .requestMatchers("/api/**", "/h2-console/**").permitAll()
    // .anyRequest().authenticated())

    // .formLogin(Customizer.withDefaults()) // Enable default login form
    // .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (enable it in
    // production)
    // .headers(headers -> headers.frameOptions().disable()); // Disable frame
    // options for H2 console

    // return http.build();
    // }
}
