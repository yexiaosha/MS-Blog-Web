package com.ms.blog.util;

import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;

/**
 * Jwt组件
 * @author wyh
 * @date 2023/01/10 13:51
 */
@Slf4j
public class JwtUtils {
    private static final String MINE_SPACE_KEY = "MineSpace";
    private static final SecretKey key = Jwts.SIG.HS256.key()
            .random(new SecureRandom(MINE_SPACE_KEY.getBytes(StandardCharsets.UTF_8)))
            .build();

    public static String createToken(String username){
        Map<String, Object> claims = new HashMap<>(20);
        claims.put("username", username);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(key)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 24L * 60 * 60 * 15 * 1000));
        return jwtBuilder.compact();
    }

    public static Map<String, Object> verifyToken(String token){

        if (token == null){
            return null;
        }

        try {
            Jwt parse = Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return (Map<String, Object>) parse.getBody();
        }catch (SignatureException e){
            log.info(e.getMessage());
            log.info("token不被信任");
            return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
