package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * 
 * @author zfang
 *
 */
public class Test13 {

	public static void main(String[] args) {
		// 先按照lastName，然后按照age排序呢
		Person people[] = new Person[] { new Person("Ted", "Neward", 10),
				new Person("Charlotte", "Neward", 41), new Person("Michael", "Naward", 19),
				new Person("Matthew", "Nmward", 13) };
		Collections.sort(Arrays.asList(people), Comparator.comparing(Person::getLastName)
				.thenComparing(Person::getAge));
		for (Person p : people) {
			System.out.println(p);
		}
	}
}
