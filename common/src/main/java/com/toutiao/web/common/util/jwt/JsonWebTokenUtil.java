package com.toutiao.web.common.util.jwt;

import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * JWT工具类
 * 一个JWT由3个json部分组成，通过base64Url编码后用.符号来分隔，最终的JWT形式：xxxxx.yyyyy.zzzzz
 * header + payload + signature
 * JWT把用户信息加密后存放在http的header中，可以不使用cookie，更加通用，跟restful配置更顺手，可以跨平台跨语言，比较适合分布式应用。
 * @author WuShoulei on 2018/1/29
 */
public class JsonWebTokenUtil {

    /**
     * JWT加密算法对应的密钥
     */
    public static final String JWT_SECRET = "@#$123";

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 签发JWT
     * SignatureAlgorithm签名算法
     * @param jti jwt的唯一身份标识,主要用来作为一次性token,从而回避重放攻击
     * @param object
     * @param ttlMillis 有效期
     * @return
     */
    public static String createJWT(String jti, Object object, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(jti)//JWT Id, 唯一
                .setIssuedAt(now)//创建时间
                .signWith(signatureAlgorithm, secretKey)
                .claim("auth", object);
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate);//过期时间
        }
        return builder.compact();
    }

    /**
     * 校验jwtStr
     * @param jwtStr
     * @return
     * @throws ExpiredJwtException,SignatureException,Exception token已过期, 签名校验失败, 其它错误
     */
    public static Claims validateJWT(String jwtStr) {
        try {
            return parseJWT(jwtStr);
        } catch (ExpiredJwtException e) {
            // TODO 可以用日志来记录错误信息
        } catch (SignatureException e) {
            // TODO 可以用日志来记录错误信息
        } catch (Exception e) {
            // TODO 可以用日志来记录错误信息
        }
        return null;
    }

    /**
     * 验证JWT
     * @param jwt
     * @return
     * @throws Exception
     */
    private static Claims parseJWT(String jwt) throws ExpiredJwtException, SignatureException,Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}