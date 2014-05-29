package test;

import java.util.Arrays;

public class Test10 {

	public static void main(String[] args) {
		Person people[] = new Person[] { new Person("Ted", "Neward", 41),
				new Person("Charlotte", "Neward", 41), new Person("Michael", "Neward", 19),
				new Person("Matthew", "Neward", 13) };

		// sort by firstName
		Arrays.sort(people, (lhs, rhs) -> lhs.firstName.compareTo(rhs.firstName));
		for (Person p : people) {
			System.out.println(p);
		}
	}

}
