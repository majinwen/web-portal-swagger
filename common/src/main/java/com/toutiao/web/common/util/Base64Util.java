package com.toutiao.web.common.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;



/**
 * 
 * 
 * 描述：Base64 编码和解码
 *
 * @author
 */
@Component
public class Base64Util {
	
	public static void main(String[] args) {
		System.out.println(Base64Util.encodeUrl("sfdfslkdsg42sd".getBytes()));
	}
	/**
	 * @描述：编码
	 */
	public static String encode(byte[] data) {
		return Base64.encodeBase64String(data);
	}

	/**
	 * @描述：编码
	 */
	public static String encodeStr(String data) {
		try {
			return encode(data.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("未知错误");
		}
	}
	/**
	 * @描述：编码为URL安全的字符串
	 */
	public static String encodeUrl(byte[] data) {
		return Base64.encodeBase64URLSafeString(data);
	}

	/**
	 * @描述：编码为URL安全的字符串
	 */
	public static String encodeUrlStr(String data) {
		try {
			return encodeUrl(data.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("未知错误");
		}
	}

	/**
	 * @描述：解码
	 */
	public static byte[] decode(String encode) {
		return Base64.decodeBase64(encode);
	}

	/**
	 * @描述：解码
	 */
	public static String decodeStr(String encode) {
		try {
			return new String(decode(encode), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("未知错误");
		}
	}
}
