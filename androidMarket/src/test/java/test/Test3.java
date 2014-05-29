package test;

import java.util.Comparator;

public class Test3 {
	public static void main(String[] args) {
		Comparator<String> c = (String lhs, String rhs) -> lhs.compareTo(rhs);
		int result = c.compare("b", "a");
		System.out.println(result);
	}
}
