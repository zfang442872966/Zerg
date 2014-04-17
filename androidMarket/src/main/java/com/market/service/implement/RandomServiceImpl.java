package com.market.service.implement;

import java.util.Random;

import com.market.service.RandomService;

public class RandomServiceImpl implements RandomService {
	public void init() {
		System.out.println("RandomServiceImpl init()...");
	}

	public String getRandomString(String string, int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = new Random().nextInt(string.length());
			sb.append(string.charAt(number));
		}
		return sb.toString();
	}

}
