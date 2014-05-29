package test;

import java.util.Arrays;
import java.util.List;

/**
 * 方法引用可以在不调用某个方法的情况下引用一个方法。构造方法引用可以在不创建对象的情况下引用一个构造方法。方法引用是另外一种实现函数式接口的方法。
 * 在某些情况下，方法引用可以进一步简化代码。比如下面的代码中，第一个forEach方法调用使用的是lambda表达式，第二个使用的是方法引用。两者作用相同，
 * 不过使用方法引用的做法更加简洁。
 * 
 * @author zfang
 *
 */
public class MethodQuote {

	public static void main(String[] args) {
		List<String> input = Arrays.asList(new String[] { "apple", "orange", "pear" });
		input.forEach((v) -> System.out.println(v));
		System.out.println("----------------------");
		input.forEach(System.out::println);
	}

}
