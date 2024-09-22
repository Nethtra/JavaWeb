package com.wty;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

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
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "wtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywty")//设置加密算法和密钥
                .setClaims(claims)//设置PayLoad的数据
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置过期时间1h  单位是毫秒
                .compact();//最后使用compact会返回String类型的生成的jwt
        System.out.println(jwt);
    }

    /**
     * 测试解析jwt令牌
     */
    @Test
    public void parseGwtTest() {
        Claims payload = Jwts.parser()
                .setSigningKey("wtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywtywty")//设置密钥
                .build()
                .parseSignedClaims("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTcyNjk5Mjg2Nn0.dugPCcNHkuA9pBCRIgalBwgj002p0IS3rQxloRT4YvQ")//放入jwt
                .getPayload();//拿到Payload
        System.out.println(payload);

    }
}
