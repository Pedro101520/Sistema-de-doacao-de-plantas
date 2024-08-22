package tech.pedrolima.DoacaoDePlantas.System;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/cadastroUsuario").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cadastrarUsuario").permitAll()
                        .requestMatchers("/", "/css/**", "/img/**", "/js/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/logar").permitAll()
                        .requestMatchers("/inicio").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .failureUrl("/login?error=true")  // Redireciona para /login com parâmetro de erro em caso de falha
                        .defaultSuccessUrl("/listagemDePlantas", true)
                        .permitAll()
                )
                .logout(config -> config
                        .logoutUrl("/logout")  // URL do logout
                        .logoutSuccessUrl("/inicio")  // Redirecionar após o logout
                        .invalidateHttpSession(true)  // Invalida a sessão HTTP
                        .deleteCookies("JSESSIONID")  // Exclui os cookies de sessão
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}