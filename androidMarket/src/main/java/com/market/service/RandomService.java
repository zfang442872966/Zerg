package com.market.service;

/**
 * 
 * @author zfang
 * 
 */
public interface RandomService {
	public final static String NUMBER = "0123456789";
	public final static String LETTER = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String CHARACTER_LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	public final static String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * Get random string
	 * 
	 * @param string
	 * @param length
	 * @return
	 */
	public String getRandomString(String string, int length);
}
