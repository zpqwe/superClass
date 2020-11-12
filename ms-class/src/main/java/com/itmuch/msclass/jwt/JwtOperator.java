package com.itmuch.msclass.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtOperator {

    public static final Logger log = LoggerFactory.getLogger(JwtOperator.class);
    /**
     * 秘钥
     * - 默认aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt
     */
    @Value("${secret:aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt}")
    private String secret;
    /**
     * 有效期，单位秒
     * - 默认2周
     */
    @Value("${expire-time-in-second:1209600}")
    private Long expirationTimeInSecond;

    /**
     * 从token中获取claim
     *
     * @param token token
     * @return claim
     */
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(this.secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            log.error("token解析错误", e);
            throw new IllegalArgumentException("Token invalided.");
        }
    }

    /**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 计算token的过期时间
     *
     * @return 过期时间
     */
    private Date getExpirationTime() {
        return new Date(System.currentTimeMillis() + this.expirationTimeInSecond * 1000);
    }

    /**
     * 为指定用户生成token
     *
     * @param claims 用户信息
     * @return token
     */
    public String generateToken(Map<String, Object> claims) {
        Date createdTime = new Date();
        Date expirationTime = this.getExpirationTime();
        byte[] keyBytes = secret.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdTime)
                .setExpiration(expirationTime)
                // 你也可以改用你喜欢的算法
                // 支持的算法详见：https://github.com/jwtk/jjwt#features
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public static void main(String[] args) {
        // 1. 初始化
        JwtOperator jwtOperator = new JwtOperator();
        jwtOperator.expirationTimeInSecond = 1209600L;
        jwtOperator.secret = "aaabbbcccdddeeefffggghhhiiijjjkkklllmmmnnnooopppqqqrrrsssttt";

        // 2.设置用户信息
        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("id", "1");

        // 测试1: 生成token
        String token = jwtOperator.generateToken(objectObjectHashMap);
        // 会生成类似该字符串的内容: eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk4MTcsImV4cCI6MTU2Njc5OTQxN30.27_QgdtTg4SUgxidW6ALHFsZPgMtjCQ4ZYTRmZroKCQ
        System.out.println(token);

        // 将我改成上面生成的token!!!
        String someToken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE2MDQ2MzgzMTEsImV4cCI6MTYwNTg0NzkxMX0.Iwk13MVSj47RsPHNOdTQzNxc4xZAR7lO7vIcZChQxjs";
        // 测试2: 如果能token合法且未过期，返回true
        Boolean validateToken = jwtOperator.validateToken(someToken);
        System.out.println(validateToken);

        // 测试3: 获取用户信息
        Claims claims = jwtOperator.getClaimsFromToken(someToken);
        System.out.println(claims);

        // 将我改成你生成的token的第一段（以.为边界）
        String encodedHeader = "eyJhbGciOiJIUzI1NiJ9";
        // 测试4: 解密Header
        byte[] header = Base64.decodeBase64(encodedHeader.getBytes());
        System.out.println(new String(header));

        // 将我改成你生成的token的第二段（以.为边界）
        String encodedPayload = "eyJpZCI6IjEiLCJpYXQiOjE2MDQ2Mzg0MjEsImV4cCI6MTYwNTg0ODAyMX0";
        // 测试5: 解密Payload
        byte[] payload = Base64.decodeBase64(encodedPayload.getBytes());
        System.out.println(new String(payload));

        // 测试6: 这是一个被篡改的token，因此会报异常，说明JWT是安全的
        jwtOperator.validateToken("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJpYXQiOjE2MDQ2Mzg0NDcsImV4cCI6MTYwNTg0ODA0N30.7dmIRo6FuYUyqSWylk_DD-cYYiyN3wqVyWc4M6dn81k");
    }
}