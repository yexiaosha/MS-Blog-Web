package com.ms.blog.util;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Jwt组件
 * @author wyh
 * @date 2023/01/10 13:51
 */
public class JwtUtils {
    private static final String KEY = "MineSpace";

    public static String createToken(String username){
        Map<String, Object> claims = new HashMap<>(20);
        claims.put("username", username);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, KEY)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 24L * 60 * 60 * 1000));
        return jwtBuilder.compact();
    }

    public static Map<String, Object> verifyToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(KEY).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
