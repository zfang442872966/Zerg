package test.thread;

public class PrimeThread extends Thread {
	long minPrime;

	PrimeThread(long minPrime) {
		this.minPrime = minPrime;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(minPrime);
		}
	}

	public static void main(String[] args) {
		PrimeThread p = new PrimeThread(0);
		PrimeThread p2 = new PrimeThread(1);
		p.start();
		p2.start();
	}
}
