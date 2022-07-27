package com.loger.phoebe.handler.utils;

import java.security.MessageDigest;
import java.util.Random;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.utils
 * @date 2022/7/26 17:21
 * @description:
 */
public class NetEaseUtils {

    private static final String SHA1 = "sha1";

    private static final String MD5 = "md5";

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 计算并获取CheckSum
     * @param appSecret
     * @param nonce
     * @param curTime
     * @return
     */
    public static String getCheckSum(String appSecret, String nonce, String curTime) {
        return encode(SHA1, appSecret + nonce + curTime);
    }

    /**
     * 计算并获取md5值
     * @param requestBody
     * @return
     */
    public static String getMD5(String requestBody) {
        return encode(MD5, requestBody);
    }

    private static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest
                    = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (byte aByte : bytes) {
            buf.append(HEX_DIGITS[(aByte >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[aByte & 0x0f]);
        }
        return buf.toString();
    }

    public static String randomStrBylen(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static Long getCurrentSeconds() {
        return System.currentTimeMillis()/1000;
    }

}
