package test;

public class Test {

	public void a() {
		this.getClass().getName();
		b();
	}

	public void b() {
	}

	public static void main(String[] args) {
		new Test2().a();
		byte[] bytes = { 8, -1, -1, -1, -1, -1, -1, -1, -1, -1, 1, 16, 15, 24, 20, 32, 0, 42, 8, 90, 84, 69, 32, 85, 57, 51, 48, 56, 7, 80, 1, 120, 1 };
		byte[] bytes2 = { 10, 15, 56, 54, 51, 57, 57, 52, 48, 49, 51, 50, 54, 50, 48, 57, 50, 18, 8, 90, 84, 69, 32, 85, 57, 51, 48 };
		System.out.println(new String(bytes));
		System.out.println(new String(bytes2));
	}
}
