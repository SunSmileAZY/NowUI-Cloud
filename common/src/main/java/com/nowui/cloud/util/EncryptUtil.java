package com.nowui.cloud.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author marcus
 * @since 2017-12-20
 */
public class EncryptUtil {

    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param content
     * @return
     */
    public static String sha256(final String content) {
        return sha(content, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param content
     * @return
     */
    public static String sha512(final String content) {
        return sha(content, "SHA-512");
    }

    /**
     * 字符串 SHA 加密
     *
     * @param content
     * @param encryptType
     * @return
     */
    private static String sha(final String content, final String encryptType) {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (content != null && content.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(encryptType);
                // 传入要加密的字符串
                messageDigest.update(content.getBytes());
                // 得到 byte 類型结果
                byte[] byteBuffer = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }
}
