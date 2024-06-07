package com.ETU.api.configs;

import com.ETU.api.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    @Autowired
    public JwtRequestFilter(JwtTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String email = null;
        String jwt = null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            jwt = authHeader.substring(7); // Вытаскиваем сам token
            try{
                email = jwtTokenUtils.getUserEmail(jwt);
            } catch (ExpiredJwtException e){
                logger.debug("JWT token is expired");
            } catch (SignatureException e){
                logger.debug("Token not validated");
            }
        }

        // Если проверка прошла успешно
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            // Собираем пользователя(UserDetails) для использования в Spring Security
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    email,
                    null,
                    jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
            );
            SecurityContextHolder.getContext().setAuthentication(token); // Делаем запрос authenticated

        }
        filterChain.doFilter(request, response);
    }
}
