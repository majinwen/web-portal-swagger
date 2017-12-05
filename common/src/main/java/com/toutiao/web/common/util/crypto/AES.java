package com.toutiao.web.common.util.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * zhangjinglei 2017/9/15 下午3:32
 */
public class AES {
    public static String encrypt2(String content, String token) {
        try {
            SecretKeySpec key = new SecretKeySpec(token.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] byteContent = content.getBytes("utf-8");
            IvParameterSpec ivspec = new IvParameterSpec("nashapolloworker".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, key,ivspec);// 初始化

            return parseByte2HexStr(cipher.doFinal(byteContent)); // 加密
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 解密算法
     *
     * @param content
     * @param token
     * @return
     */
    public static String decrypt2(String content, String token) {
        try {
            byte[] byteContent = _parseHexStr2Byte(content);

            SecretKeySpec key = new SecretKeySpec(token.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            IvParameterSpec ivspec = new IvParameterSpec("nashapolloworker".getBytes());

            cipher.init(Cipher.DECRYPT_MODE, key, ivspec);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将16进制转换为二进制
     *
     * @param hexStr
     * @return
     * @author
     * @version 2015年11月7日  下午9:22:04
     */
    private static byte[] _parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
