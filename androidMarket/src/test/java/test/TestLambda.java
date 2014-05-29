package test;

/**
 * 函数式接口是只包含一个抽象方法的接口. 在使用lambda表达式时，只需要提供形式参数和方法体。由于函数式接口只有一个抽象方法，
 * 所以通过lambda表达式声明的方法体就肯定是这个唯一的抽象方法的实现，而且形式参数的类型可以根据方法的类型声明进行自动推断。
 * 
 * 最好在接口上使用注解@FunctionalInterface进行声明
 * 
 * @author zfang
 *
 */
public class TestLambda {

	public static void main(String[] args) {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("匿名内部类");
			}
		};
		r.run();

		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("传统的创建一个线程并运行的方式");
			}
		}).start();

		// lambda
		new Thread(() -> {
			System.out.println("lambda!");
		}).start();
	}

}
