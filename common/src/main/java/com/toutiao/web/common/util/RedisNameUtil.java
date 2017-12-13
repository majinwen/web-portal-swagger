package com.toutiao.web.common.util;

/**
 * MemcacheKey
 * @author YZP
 */
public class RedisNameUtil {

	/**
	 * Redis前缀
	 */
	public static final String USER_KEY = "webUser_";


	/**
	 * 储存时间(秒)
	 */
	public static final int time = 1200;
	
	/**
	 * 计数
	 */
	public static final String separativeSignCount = "_@@@@@count";
	/**
	 * 分隔符
	 */
	public static final String separativeSign = "@@@@@";



}
