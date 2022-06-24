package top.hackchen.secondhandmarket.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 *
 */
public class EncryptUtils {
    private static String buildInEncrypt(String algorithm, String text) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(text.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("没有名为" + algorithm + "的内置加密方法！");
        }
    }

    public static String md5(String text) {
        return buildInEncrypt("md5", text);
    }

    public static String sha256(String text) {
        return buildInEncrypt("sha-256", text);
    }

    public static String passwordEncrypt(String md5Password, String salt) {
        return sha256(sha256(salt) + md5Password);
    }

    /**
     * 验证密码是否正确
     *
     * @param md5Password       md5加密后的密码
     * @param salt              未处理的盐
     * @param encryptedPassword 数据库中待比对的密码
     * @return 密码是否正确
     */
    public static boolean verifyPassword(String md5Password, String salt, String encryptedPassword) {
        return EncryptUtils.passwordEncrypt(md5Password, salt).equals(encryptedPassword);
    }
}
