package test;

public class Jdk8Feature {

	public static void main(String[] args) {
		// lambda是方法的实现
		// lambda是延迟执行的
		// lambda包含3个部分：
		// （1）括弧包起来的参数
		// （2）一个箭头
		// （3）方法体，可以是单个语句，也可以是语句块
		Runnable r = () -> System.out.println("hello,lambda");
		r.run();
	}
}
