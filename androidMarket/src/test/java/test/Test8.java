package test;

public class Test8 {

	public static void main(String[] args) {
		String message = "Howdy, world!";
		Runnable r = () -> System.out.println(message);
		r.run();
	}
}
