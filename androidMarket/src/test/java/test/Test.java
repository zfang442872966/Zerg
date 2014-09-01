package test;

public class Test {

	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(20000));
		 System.out.println(Integer.toBinaryString(78));
		 System.out.println(Integer.toBinaryString(32));
		// System.out.println(Integer.toHexString(15));
		// System.out.println(Integer.toOctalString(15));
		// System.out.println(Integer.parseInt("D8", 16));
		// System.out.println(Integer.toBinaryString(0x89D8));
		System.out.println(Integer.parseInt("0", 16));
		System.out.println(Integer.parseInt("7FFF", 16));
		System.out.println(Integer.parseInt("8000", 16));
		System.out.println(Integer.parseInt("9FFF", 16));
		System.out.println(Integer.parseInt("A000", 16));
		System.out.println(Integer.parseInt("FFFF", 16));
		System.out.println("---------------------");
		System.out.println(Integer.parseInt("1000", 16));
		System.out.println("---------------------");
		//8086CPU的地址总线宽度为20，可以定位2^20个内存单元
		System.out.println(Math.pow(2, 20));
		//80386CPU的地址总线为32
		System.out.println(Math.pow(2, 32));
		System.out.println("---------------------");
		System.out.println(Integer.parseInt("8226", 16));
		System.out.println(Integer.parseInt("FFFF", 16));
		
	}
}
