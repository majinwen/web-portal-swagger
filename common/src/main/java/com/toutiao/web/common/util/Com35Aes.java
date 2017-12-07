package com.toutiao.web.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Com35Aes {
	// cookie登录JSON加密KEY
	public static final String KEYCODE = "0123456789abcdefghij*&!%$qrstuvwxyzA^Z";
	/** 字符串加密 */
	public static final String STRING_KEYCODE = "0123456789abcdefghijklmnopqrstuvwxyzA^Z";
	/**
	 * 传入KEY
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static byte[] getLocalKey(String keycode) throws NoSuchAlgorithmException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
		secureRandom.setSeed(keycode.getBytes());
		kgen.init(128, secureRandom);
		SecretKey secretKey = kgen.generateKey();
		return secretKey.getEncoded();
	}


	/**
	 * 功能描述：AES加密字符串
	 *
	 * @param keycode
	 * @param text
	 */
	public static final String encrypt(String keycode, String text) {
		try {
			byte[] myKey = getLocalKey(keycode);
			SecretKeySpec key = new SecretKeySpec(myKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = text.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent); // 加密
			return ByteHex.byte2HexStr(result);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 功能描述：AES解密
	 *
	 * @param keycode
	 * @param text
	 * @return
	 */
	public static final String decrypt(String keycode, String text) {
		try {
			byte[] myKey = getLocalKey(keycode);
			byte[] btext = ByteHex.hexStr2byte(text);
			SecretKeySpec key = new SecretKeySpec(myKey, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(btext); // 解密
			return new String(result, "utf-8");
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加密，解密如何使用
	 * 
	 * @param args
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		// 加密321500000135012 7313401370
		/*List<Long> list1 = new ArrayList<Long>();
		long a = 65432000000029l;
		for(int i=0;i<30;i++){
			list1.add(a-i);
		}
		List<String> list = new ArrayList<String>();
		
		list.add("80CE799FAAA9CB17FEAA96DDA5586C36");
		list.add("8EB0B43B5957C8F3EF5F772341711050");
		list.add("C5B385AB575136F16174E05AC34A7714");
		list.add("512F82963AEBDBF97C21C0CD653B7AD8");
		list.add("A15C4510110908F172A63E06B89FD84E");
		list.add("BC7ED96DFD4A9EB2616D7304EC2C26E8");
		list.add("B8FF675E8CAE24E7B1F68DD255553CB9");
		list.add("EE43D452769689F6D5F2E5CE2FEC5F9D");
		list.add("E435BA2B150C0EADA655D822C4966046");
		list.add("759717E260412DA07EB906A9264FCBEB");
		list.add("1B8914D632870724A06D7813441BAD86");
		list.add("A7FEAC787FB5A18634C61B00BF3DA3DF");
		list.add("3B5445453DAFAC5B75A55AEBD721CDFA");
		list.add("BE32C7B5A7355FD037DF4A9C410595F1");
		list.add("CB9F55D9D4CCF98A4EE368C48743C03B");
		list.add("BC200FDEFDB5D4124B52F92E6640FF6A");
		list.add("6CDE4103E78B723E1BFD83A34E271E32");
		list.add("9DBAF94429F860A8D8540681C12A8ED5");
		list.add("197BC8069E69831F93D0E3905C90A1CA");
		list.add("54A4A087A0FF5165A8AC3FDDD98B9592");
		list.add("AE66FC31EB4C1E5CD6991D7B13A18864");
		list.add("756F2CD7E910DEBD205E73959DD9D740");
		list.add("41AA525D6E36B9E01E0B7B7C470B81AD");
		list.add("6DEBD5E1E80B89CC63EA73EE36B6F847");
		list.add("3767509849DA60DF297E0BE0491395C9");
		list.add("F2229D33212B2E468387B87FD86ADC54");
		list.add("4239DCCEE16065AD16118099FC5ABC7B");
		list.add("0FFDA62F666508B8EA5E9293702B778F");
		list.add("615A79E6E3CBE5FD888C578B17F85EE7");
		list.add("1010ED08F9EF75C8D6EACD1F1F7B8DAF");
		int i = 0;
		for(String str :list){
			String strEn = decrypt(Com35Aes.STRING_KEYCODE, str);
			System.out.println(list1.get(i).toString()+":"+strEn);
			i++;
		}*/
		//String strE = encrypt(KEYCODE, "MM521");
		//System.out.println("out:"+strE);
		//String strD = decrypt(KEYCODE, strE);
		//System.out.println("inTrue:"+strD);
	}
}
