package com.funnycode.react_springboot_account.config;


import com.funnycode.react_springboot_account.dto.TokenPayload;
import com.funnycode.react_springboot_account.entity.Account;
import com.funnycode.react_springboot_account.exception.CustomHandleException;
import com.funnycode.react_springboot_account.mapper.AccountMapper;
import com.funnycode.react_springboot_account.repository.AccountRepository;
import com.funnycode.react_springboot_account.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.DataSource;
import java.io.IOException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Configuration
public class JwtAuthenticationRequest extends OncePerRequestFilter {

    private final AccountRepository accountRepository;
    private final JwtTokenUtils jwtTokenUtils;

//    @Bean
//    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("com.funnycode.react_springboot_account.entity");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        em.setJpaVendorAdapter(vendorAdapter);
//        em.afterPropertiesSet();
//        return em.getObject();
//    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

       //check token

        String authHeader = request.getHeader("Authorization");
        String token = null;
        TokenPayload tokenPayload = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            //lấy token
            token = authHeader.substring(7).trim();
            //lấy ra tokenPayload từ token
            try{
                tokenPayload = jwtTokenUtils.getTokenPayloadFromToken(token);

            } catch(IllegalArgumentException ex){
            System.out.println("Unable to get JWT: "+ex.getMessage());
            }catch(ExpiredJwtException ex) {
                System.out.println("Token has exp: "+ex.getMessage());
            }

        }

            if(tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null){
                Optional<Account> optionalAccount = accountRepository.findByEmail(tokenPayload.getEmail());
                if(optionalAccount.isPresent()){
                    Account account = optionalAccount.get();
                    if (jwtTokenUtils.validateToken(token, AccountMapper.toTokenPayload(account))){

                        List<GrantedAuthority> authorities = tokenPayload.getAuthorities().stream()
                                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

                        UserDetails userDetails = new org.springframework.security.core.userdetails.User(account.getEmail()
                        ,account.getPassword(), authorities);

                        UsernamePasswordAuthenticationToken authentication = new
                                UsernamePasswordAuthenticationToken(userDetails,null, authorities);

                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        filterChain.doFilter(request,response);

    }


}
