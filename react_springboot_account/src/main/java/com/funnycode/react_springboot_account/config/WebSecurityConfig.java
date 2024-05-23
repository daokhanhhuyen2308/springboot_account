package com.funnycode.react_springboot_account.config;


import com.funnycode.react_springboot_account.utils.Endpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationRequest jwtAuthenticationRequest;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.requestMatchers(Endpoints.PUBLIC).permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/product/add").hasAnyAuthority("ADMIN", "MANAGER")
                        .requestMatchers(Endpoints.PRIVATE).hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/auth/update/**").hasAnyAuthority("USER")
                        .requestMatchers(HttpMethod.GET, "/api/auth/account").hasAnyAuthority("USER")
                        .requestMatchers("/api/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
             httpSecurity.addFilterBefore(jwtAuthenticationRequest, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();

        }

}
