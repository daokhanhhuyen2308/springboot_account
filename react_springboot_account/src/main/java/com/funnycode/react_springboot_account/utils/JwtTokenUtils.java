package com.funnycode.react_springboot_account.utils;

import com.funnycode.react_springboot_account.dto.TokenPayload;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenUtils {

    public static final String SECRET_KEY = "coding_with_me";

    public long exp = 2592000;
    public String generateToken(TokenPayload tokenPayload, long exp) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("payload",tokenPayload);

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() * 1000))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();

    }

    public TokenPayload getTokenPayloadFromToken(String token) {
        return getClaimFromToken(token, (Claims claims) -> {
            Map<String, Object> result = (Map<String,Object>) claims.get("payload");
            return TokenPayload.builder().id((int) result.get("id"))
                    .username((String) result.get("username"))
                    .email((String) result.get("email"))
                    .authorities(new HashSet<>((List<String>) result.get("authorities"))).build();
        });
    }


    private <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public boolean validateToken(String token, TokenPayload tokenPayload) {
        TokenPayload payload = getTokenPayloadFromToken(token);

//        System.out.println("TokenPayload authorities: " + payload.getAuthorities());
//        System.out.println("TokenPayload username: " + payload.getUsername());
//        System.out.println("TokenPayload email: " + payload.getEmail());
//        System.out.println("TokenPayload id: " + payload.getId());
//
//        // Log dữ liệu từ payload
//        System.out.println("Payload authorities: " + tokenPayload.getAuthorities());
//        System.out.println("Payload username: " + tokenPayload.getUsername());
//        System.out.println("Payload email: " + tokenPayload.getEmail());
//        System.out.println("Payload id: " + tokenPayload.getId());

        return payload.getUsername().equals(tokenPayload.getUsername())
                && payload.getEmail().equals(tokenPayload.getEmail())
                && payload.getAuthorities().containsAll(tokenPayload.getAuthorities())
                && (payload.getId() == tokenPayload.getId())
                && !isExp(token);
    }

    public boolean isExp(String token) {
        Date expDate = getClaimFromToken(token, Claims::getExpiration);
        return expDate.before(new Date());
    }


}
