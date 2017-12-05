package com.toutiao.web.common.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;



public class Hmactest {
    public static String s(String data,String key){

        byte[] bytes = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_1,key.getBytes()).doFinal(data.getBytes());
        Base64 base64=new Base64(true);

//        return base64.encodeToString(bytes);
        return new Hex().encodeHexString(bytes);
    }
}
