// package com.pokedex.pokedex_api.config;

// import java.io.IOException;
// import java.util.ArrayList;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import com.pokedex.pokedex_api.entities.UserEntity;
// import com.pokedex.pokedex_api.service.UserService;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;

// @Component
// public class TokenAuthenticationFilter extends OncePerRequestFilter {

//     private final UserService userService;

//     public TokenAuthenticationFilter(UserService userService) {
//         this.userService = userService;
//     }

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {
//         String authHeader = request.getHeader("Authorization");

//         if (authHeader != null && authHeader.startsWith("Bearer ")) {
//             String token = authHeader.substring(7);
//             System.out.println("Token recebido: " + token); // Log do token recebido
//             UserEntity user = userService.findByToken(token);

//             if (user != null) {
//                 // Token válido, definir a autenticação no contexto de segurança
//                 System.out.println("Usuário autenticado: " + user.getUsername()); // Log do usuário autenticado
                
//                 // Criar um objeto de autenticação e definir no contexto de segurança
//                 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                         user, null, new ArrayList<>());
//                 SecurityContextHolder.getContext().setAuthentication(auth);

//                 filterChain.doFilter(request, response);
//                 return;
//             } else {
//                 System.out.println("Usuário não encontrado para o token: " + token); // Log se o usuário não for encontrado
//             }
//         } else {
//             System.out.println("Token inválido ou não fornecido."); // Log para token inválido
//         }

//         // Se o token não for válido, continue a cadeia (mas não autentique o usuário)
//         filterChain.doFilter(request, response);
//     }
// }
