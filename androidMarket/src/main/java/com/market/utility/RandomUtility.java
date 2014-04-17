package com.market.utility;

import java.util.Random;

public class RandomUtility {
	public final static String NUMBER = "0123456789";
	public final static String LETTER = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String CHARACTER_LETTER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public final static String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
	public final static String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String getRandomString(String string, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = new Random().nextInt(string.length());
			sb.append(string.charAt(number));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Random().nextInt(10));
	}
}
