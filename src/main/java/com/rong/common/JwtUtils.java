package com.rong.common;

import io.jsonwebtoken.*;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * @description:
 * @author: QR
 * @create: 2020-01-05 13:59
 **/
public class JwtUtils {
    /**
     * 生成token
     * @param id 随机生成的UUID
     * @param subject  负载的信息
     * @param ttlMillis 过期时间
     * @return token
     */
    public static String createJwt(String id, String subject, Long ttlMillis) {
        //指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //当前系统时间
        long nowMillis = System.currentTimeMillis();
        //令牌签发时间
        Date now = new Date(nowMillis);

        //如果令牌有效期为null，则默认设置有效期1小时
        if(ttlMillis==null){
            ttlMillis=Constants.JWT_TTL;
        }

        //令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        //生成秘钥
        SecretKey secretKey = generalKey();

        //封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder()
                //唯一的ID
                .setId(id)
                // 主题  可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer(Constants.JWT_ROLE)
                // 签发时间
                .setIssuedAt(now)
                // 签名算法以及密匙
                .signWith(signatureAlgorithm, secretKey)
                // 设置过期时间
                .setExpiration(expDate);
        return builder.compact();
    }

    /**
     * 生成加密 secretKey
     * @return 密钥
     */
    public static SecretKey generalKey() {
        // 加密
        byte[] encodedKey = Base64.getEncoder().encode(Constants.JWT_KEY.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 解析令牌数据
     * @param jwt token
     * @return 解析后的载荷数据
     * @throws Exception
     */
    public static Claims parseJwt(String jwt) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
