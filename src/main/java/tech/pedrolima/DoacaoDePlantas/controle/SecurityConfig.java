package tech.pedrolima.DoacaoDePlantas.controle;

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
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/", "/inicio", "/login", "/cadastroUsuario", "/css/**", "/img/**", "/js/**").permitAll()
                                .anyRequest().authenticated() // Todas as outras URLs precisam de autenticação
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                //Página que o sistema vai redirecionar o usuário, se o login for correto
                                .defaultSuccessUrl("/listagemDePlantas", true)
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .permitAll()
                );
        return http.build();
    }
}
