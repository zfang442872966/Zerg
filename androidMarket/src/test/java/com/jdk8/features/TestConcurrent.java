package com.jdk8.features;

public class TestConcurrent {

	public static void mian(String[] args) {
		new Thread(() -> {
			System.out.println("The original");
		}).start();

	}
}
