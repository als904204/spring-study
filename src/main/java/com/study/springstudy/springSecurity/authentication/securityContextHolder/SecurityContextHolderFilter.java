package com.study.springstudy.springSecurity.authentication.securityContextHolder;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Slf4j

@Component
public class SecurityContextHolderFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().startsWith("/contextHolder")) {
            log.info("contextHolder 실행");

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            Authentication principal = new TestingAuthenticationToken("username", "password", "ROLE_USER");
            securityContext.setAuthentication(principal);
            SecurityContextHolder.setContext(securityContext);

            Authentication authentication = securityContext.getAuthentication();
            String getName = authentication.getName();
            var getPrincipal = authentication.getPrincipal();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            log.info("getName={}",getName);
            log.info("getPrincipal={}",getPrincipal);
            log.info("getAuthorities={}",authorities);

            return;
        }
        filterChain.doFilter(request,response);
    }
}
