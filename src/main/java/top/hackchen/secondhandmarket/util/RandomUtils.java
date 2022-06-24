package top.hackchen.secondhandmarket.util;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class RandomUtils {

    private static final Random random = new Random();

    private static final DecimalFormat fourDigits = new DecimalFormat("0000");

    private static final DecimalFormat sixDigits = new DecimalFormat("000000");

    //生成4位随机数
    public static String getFourBitRandom() {
        return fourDigits.format(random.nextInt(10000));
    }
    //生成6位随机数
    public static String getSixBitRandom() {
        return sixDigits.format(random.nextInt(1000000));
    }

    /**
     * 返回一个32位的随机字符串
     *
     * @return 32位的随机字符串
     */
    public static String createRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}