package com.aptota.featuretoggle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AppConfig appConfig;

    private static final String[] WHITELIST = {
            "/users/register",
            "/greeting"
    };

    public AppSecurityConfig(UserDetailsService userDetailsService, AppConfig appConfig) {
        this.userDetailsService = userDetailsService;
        this.appConfig = appConfig;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(appConfig.bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(request ->
                        request.requestMatchers(WHITELIST).permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                //.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }


}