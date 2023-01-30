package com.ms.blog.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * Jwt组件
 * @author wyh
 * @date 2023/01/10 13:51
 */
@Slf4j
public class JwtUtils {
    private static final String KEY = "MineSpace";

    public static String createToken(String username){
        Map<String, Object> claims = new HashMap<>(20);
        claims.put("username", username);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, KEY)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24L * 60 * 60 * 15 * 1000));
        return jwtBuilder.compact();
    }

    public static Map<String, Object> verifyToken(String token){

        if (token == null){
            return null;
        }

        try {
            Jwt parse = Jwts.parser().setSigningKey(KEY).parse(token);
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
