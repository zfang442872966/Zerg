package test;

public class Hello {
	public Runnable r = new Runnable() {
		public void run() {
			System.out.println(Hello.this);
			System.out.println(Hello.this.toString());
		}
	};

	public String toString() {
		return "Hello's custom toString()";
	}

	public static void main(String[] args) {
		Hello h = new Hello();
		h.r.run();
	}
}
