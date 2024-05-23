//package com.funnycode.react_springboot_account.service;
//
//import com.funnycode.react_springboot_account.entity.Token;
//import com.funnycode.react_springboot_account.repository.TokenRepository;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//
//import java.util.Optional;
//
//@RequiredArgsConstructor
//public class LogoutService implements LogoutHandler {
//
//    private final TokenRepository tokenRepository;
//
//    @Override
//    public void logout(HttpServletRequest request, HttpServletResponse response,
//                       Authentication authentication) {
//
//        final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwt = authHeader.substring(7).trim();
//
//            Optional<Token> storedToken = tokenRepository.findByToken(jwt);
//
//            if (storedToken.isPresent()){
//                Token token = storedToken.get();
//                token.setExpired(true);
//                token.setRevoked(true);
//                tokenRepository.save(token);
//                SecurityContextHolder.clearContext();
//            }
//        }
//
//    }
//}
