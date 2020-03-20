package com.example.utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 加密工具类
 * 用于加密明文密码(MD5)
 */
public class EncryptUtils {
    private static final String ALGO = "MD5";
    private static final Logger logger = Logger.getLogger(EncryptUtils.class);

    public static String encrypt(String password) {
        logger.debug("start encrypt, password : "+ password);

        BigInteger bigInteger = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGO);
            byte[] pwData = password.getBytes();
            messageDigest.update(pwData);
            bigInteger = new BigInteger(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("encrypt error.");
        }
        // 加密后为128bit，十六进制32位字符串
        String ret = bigInteger.toString();
        logger.debug("encrypt completed, md5: " + ret);
        return ret;

    }
}
