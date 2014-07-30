package com.jdk8.features;

/**
 * 只包含一个方法的接口被称为功能接口，Lambda 表达式用用于任何功能接口适用的地方。
 * 
 * 最好在接口上使用注解@FunctionalInterface进行声明
 * 
 * @author zfang
 *
 */
public class TestLambda {

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("匿名内部类");
			}
		};
		r.run();
		
		String b = "aaa";
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("传统的创建一个线程并运行的方式");
				System.out.println(b);
			}
		}).start();
		
		String a = "aaa";
		// lambda
		new Thread(() -> {
			System.out.println("lambda!");
			System.out.println(a);
		}).start();
	}

}
