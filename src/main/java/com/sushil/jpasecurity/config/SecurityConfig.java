package com.sushil.jpasecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final UserDetailsService jpaUserDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.jpaUserDetailsService = userDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/h2-console/**").permitAll()
//                        .requestMatchers("/api/posts").permitAll()
//                        .anyRequest().authenticated())
//                .userDetailsService(jpaUserDetailsService)
//                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
//                .httpBasic(Customizer.withDefaults())
//                .build();
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/posts").permitAll()
                        .anyRequest().authenticated())
                .userDetailsService(jpaUserDetailsService)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .formLogin(this::getFormLoginCustomizer)
                .logout(this::getLogoutCustomizer)
                .build();
    }

    private void getLogoutCustomizer(LogoutConfigurer<HttpSecurity> logout) {
        logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
    }

    private void getFormLoginCustomizer(FormLoginConfigurer<HttpSecurity> form) {
        form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .permitAll();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
