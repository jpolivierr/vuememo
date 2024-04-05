package com.appvenir.vuememo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                    .authorizeHttpRequests( auth -> auth
                                       .requestMatchers(allowedPath()).permitAll()
                                       .anyRequest().authenticated()
                                                 
                    )
                    .formLogin( login -> {
                        login.loginPage("/login")
                             .usernameParameter("email")
                             .defaultSuccessUrl("/dashboard", true)
                             .failureUrl("/login?error=true");
                        })
                    .headers( headers -> headers.frameOptions( option -> option.sameOrigin()))
                    .build(); 
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public String[] allowedPath(){
        return new String[]{
                            "/signup/**",
                            "/login/**",
                            "/assets/**"
                        };
    }
    
}
