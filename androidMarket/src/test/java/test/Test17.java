package test;

import java.util.Arrays;
import java.util.List;

public class Test17 {

	public static void main(String args[]) {
		List<Integer> values = Arrays.asList(1, 2, 3, 4, 5);
		int sum = values.stream().reduce(0, (l, r) -> l + r);
		System.out.println(sum);
	}
}
