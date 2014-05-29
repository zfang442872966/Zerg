package testinterface;

public interface TestInterface {

	default void test() {
		System.out.println("test");
	}

	default void test2() {
		System.out.println("test");
	}
}
