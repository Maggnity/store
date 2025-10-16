package com.codewithmaggnity.store.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 🔒 Desabilita CSRF (não usamos sessões)
                .csrf(csrf -> csrf.disable())

                // 🔒 Define políticas de autorização
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/login", // login é público
                                "/login", // página HTML de login (se existir)
                                "/css/**",
                                "/js/**")
                        .permitAll()
                        .requestMatchers(
                                "/*",
                                "/js/**")
                        .permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().authenticated() // todo o resto precisa de token válido
                )

                // 🔒 Desabilita sessão — autenticação será feita por token a cada requisição
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 🔒 Tratamento de erros de autenticação
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.getWriter().write("Acesso não autorizado: token inválido ou ausente");
                        }))

                // 🔒 Adiciona o filtro JWT antes do filtro padrão de login
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
