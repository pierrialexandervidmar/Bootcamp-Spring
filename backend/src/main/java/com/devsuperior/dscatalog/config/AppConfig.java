package com.devsuperior.dscatalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Classe de configuração responsável por definir beans que serão utilizados no contexto da aplicação.
 * Fornece a configuração de beans comuns, como o encoder de senha.
 */
@Configuration
public class AppConfig {

    /**
     * Define um bean do tipo {@link BCryptPasswordEncoder} que será utilizado para criptografar senhas.
     * O BCrypt é uma função de hash usada para garantir a segurança das senhas, gerando um hash
     * que pode ser verificado, mas nunca facilmente decodificado.
     *
     * @return Um {@link BCryptPasswordEncoder} configurado.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
