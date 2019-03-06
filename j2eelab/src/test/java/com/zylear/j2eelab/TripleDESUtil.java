package com.zylear.j2eelab;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class TripleDESUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(TripleDESUtil.class);
	private static String encryptKey = "";

	public static final String KEY_ALGORITHM = "DESede";
	public static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";


	public static void setEncryptKey(String encryptKey) {
		TripleDESUtil.encryptKey = encryptKey;
	}

	/**
	 * 生成密钥
	 * Java6只支持56位密钥
	 * BouncyCastle支持64位密钥，官网是http://www.bouncycastle.org/
	 */
	public static String initkey() throws NoSuchAlgorithmException {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(168);
		SecretKey secretKey = kg.generateKey();
		return Base64.encodeBase64String(secretKey.getEncoded());
	}

	/**
	 * 转换密钥
	 */
	private static Key toKey(byte[] key) throws Exception {
		DESedeKeySpec dks = new DESedeKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
	}
	
	/**
	 * 加密数据
	 * @param data 待加密数据
	 * @param key  密钥
	 * @return 加密后的数据
	 */
	public static String encrypt(String data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
	}
	
	/**
	 * 解密数据
	 * @param data 待解密数据
	 * @return 解密后的数据
	 */
	public static String decrypt(String data) throws Exception {
		Key k = toKey(Base64.decodeBase64(encryptKey));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return new String(cipher.doFinal(Base64.decodeBase64(data)));
	}
	
	/**
	 * 加密数据
	 * @param data 待加密数据
	 * @return 加密后的数据
	 */
	public static String encrypt(String data) throws Exception {
		Key k = toKey(Base64.decodeBase64(encryptKey));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
	}
	
	/**
	 * 解密数据
	 * @param data 待解密数据
	 * @param key  密钥
	 * @return 解密后的数据
	 */
	public static String decrypt(String data, String key) throws Exception {
		Key k = toKey(Base64.decodeBase64(key));
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return new String(cipher.doFinal(Base64.decodeBase64(data)));
	}
	
//	public static void main(String[] args) throws Exception {
//		String source = "256646464654684964646455";
//		System.out.println("原文: " + source);
//		
//		for (int i = 0; i < 2000; i++) {
//			String key = encryptKey;
//			System.out.println("密钥: " + key);
//			
//			long t1 = new Date().getTime();
//			String encryptData = encrypt(source, key);
//			System.out.println("加密: " + encryptData);
//			
//			String decryptData = decrypt(encryptData, key);
//			System.out.println("解密: " + decryptData);
//			System.out.println(new Date().getTime() -t1);
//		}
//
//		String decryptData = decrypt("F1EK/V62n+9MuDEzaz4XrGE60+BYzGde");
//		System.out.println(decryptData);
//	}

	public static void main(String[] args) {
		String string = "wxid_efabkodxu6ft22\u001A ";

		System.out.println(string.replaceAll("[^0-9_a-zA-Z]",""));
	}
}
