package test;

public class Hello2 {
	public Runnable r = () -> {
		System.out.println(this.getClass().getName());
		System.out.println(toString());
	};

	public String toString() {
		return "Hello's custom toString()";
	}

	public static void main(String[] args) {
		Hello h = new Hello();
		h.r.run();
	}
}
