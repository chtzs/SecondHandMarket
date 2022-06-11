package top.hackchen.secondhandmarket.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import top.hackchen.secondhandmarket.beans.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LoginUtils {
    private static final long EXPIRE_DAY = 14;
    private static final long SECOND_PER_DAY = 60 * 60 * 24;
    private static final long EXPIRE_TIME_SECOND = SECOND_PER_DAY * EXPIRE_DAY;
    private static final String password;

    static {
        password = EncryptUtils.createRandomUUID();
    }

    public static String createToken(Serializable userId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expire = now.plusSeconds(EXPIRE_TIME_SECOND);
        String secret = userId + password;
        return JWT.create()
                .withAudience(userId.toString())
                .withIssuedAt(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .withExpiresAt(Date.from(expire.atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC256(secret));
    }

    public static boolean verifyToken(String token) {
        String secret = getId(token) + password;
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    public static String getId(String token) {
        try {
            return JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            return "-1";
        }
    }
}
