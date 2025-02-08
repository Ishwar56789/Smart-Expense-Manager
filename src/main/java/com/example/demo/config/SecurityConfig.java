package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
        return https
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(request -> 
                        request
                            .requestMatchers("/api/user/**").permitAll()
                            .anyRequest()
                            .authenticated()
                    )
                    .httpBasic(Customizer.withDefaults())
                    .sessionManagement(session -> 
                        session
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    )
                    .build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    // @Bean
    // public AuthenticationProvider authenticationProvider() {
    //     DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //     daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(10));
    //     daoAuthenticationProvider.setUserDetailsService(null);
    //     return daoAuthenticationProvider;
    // }

}
