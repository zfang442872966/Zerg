package test;

import java.util.Comparator;

public class Test4 {

	public static void main(String[] args) {
		Comparator<String> c = (lhs, rhs) -> {
			System.out.println("I am comparing " + lhs + " to " + rhs);
			return lhs.compareTo(rhs);
		};
		int result = c.compare("Hello", "World");
		System.out.println(result);
	}

}
