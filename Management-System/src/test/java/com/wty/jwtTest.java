package com.wty;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王天一
 * @version 1.0
 */

public class jwtTest {
    /**
     * 测试生成jwt令牌
     */
    @Test
    public void genGwtTest() {
        //设置payload数据
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 123);
        claims.put("name", "tom");

        // 自定义密钥字符串
        String secretKeyStr = "my-super-secret-key-12345-54321王王王王";
        // 将密钥字符串转换为 SecretKey
        SecretKey key = Keys.hmacShaKeyFor(secretKeyStr.getBytes());
        //SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);   也可以生成密钥 但是都要保证生成令牌和解析令牌用的是同一个密钥
        //SecretKey key = Jwts.SIG.HS256.key().build();
        // 创建 JWT 令牌   jwts是jwt依赖提供的工具类
        String jwtToken = Jwts.builder()
                //设置加密算法和密钥，不指定加密算法也可以 默认就是hs256
                .signWith(SignatureAlgorithm.HS256, key)
                //设置payload的数据
                .claims(claims)
                //设置过期时间1h
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                //最后拼接成String类型
                .compact();
        System.out.println("生成的 JWT 令牌: " + jwtToken);

    }

    /**
     * 测试解析jwt令牌
     */
    @Test
    public void parseGwtTest() {
        CharSequence jws = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEyMywiZXhwIjoxNzQwNjU1NDk2fQ.X-Bn9ZmbZQWKSoWK96wLa_sxIDBpOb_rSYnlqP62Ikg";
        String secretKeyStr = "my-super-secret-key-12345-54321王王王王";
        SecretKey secretKey = Keys.hmacShaKeyFor(secretKeyStr.getBytes());

        Claims payload = Jwts.parser()
                //设置密钥
                .verifyWith(secretKey)
                .build()
                //jws
                .parseSignedClaims(jws)
                .getPayload();
        System.out.println("验证成功,payload是："+payload);
    }
}
