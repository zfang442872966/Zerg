package test;

public class Test5 {

	public static void main(String[] args) {
		Object r = (Runnable) () -> System.out.println("hello,lambda");
	}

}
