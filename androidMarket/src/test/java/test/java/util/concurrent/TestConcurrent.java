package test.java.util.concurrent;

public class TestConcurrent {

	public static void mian(String[] args) {
		new Thread(() -> {
			System.out.println("The original");
		}).start();

	}
}
