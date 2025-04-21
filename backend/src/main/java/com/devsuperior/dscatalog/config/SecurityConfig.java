package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desabilita todas as requisições de passarem pela autenticação
        http.headers(headers -> headers
                .frameOptions(frameOptions -> frameOptions.sameOrigin()) // <-- Permite frames da mesma origem
            ).authorizeHttpRequests((authz) -> authz.anyRequest().permitAll());

        // Desativa a proteção CSRF (Cross-Site Request Forgery), geralmente usado em apps não baseados em navegação web
        http.csrf().disable();

        return http.build();
    }
}
