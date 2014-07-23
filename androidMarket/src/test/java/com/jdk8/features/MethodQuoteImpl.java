package com.jdk8.features;

import java.util.Arrays;
import java.util.List;

/**
 * 
 */
public class MethodQuoteImpl {

	public void test(MethodQuote t) {
		System.out.println(t.lambdaTest(2, 3));
	}

	public static void main(String[] args) {
		List<String> input = Arrays.asList(new String[] { "apple", "orange", "pear" });
		input.forEach(System.out::println);
		input.forEach((string) -> System.out.println(string));

		MethodQuoteImpl m = new MethodQuoteImpl();
		// 匿名内部类的方式
		m.test(new MethodQuote() {
			@Override
			public int lambdaTest(int a, int b) {
				return a + b;
			}
		});

		// lambda表达式
		m.test((a, b) -> {
			return a + b;
		});
	}
}
