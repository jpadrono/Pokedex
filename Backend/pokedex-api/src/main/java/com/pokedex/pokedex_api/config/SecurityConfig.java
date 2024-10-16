// package com.pokedex.pokedex_api.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final TokenAuthenticationFilter tokenAuthenticationFilter;

//     public SecurityConfig(TokenAuthenticationFilter tokenAuthenticationFilter) {
//         this.tokenAuthenticationFilter = tokenAuthenticationFilter;
//     }

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())  // Desabilita CSRF para facilitar os testes
//             .authorizeHttpRequests(authorize -> authorize
//                 .requestMatchers("/user/login", "/user/create").permitAll()  // Permite acesso público às rotas de login e criação de usuário
//                 .anyRequest().authenticated()  // Exige autenticação para todas as outras rotas
//             )
//             .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Adiciona o filtro de autenticação

//         return http.build();  // Constrói a configuração de segurança
//     }
// }

