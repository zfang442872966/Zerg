package test.thread;

public class PrimeRun implements Runnable {
	long minPrime;

	PrimeRun(long minPrime) {
		this.minPrime = minPrime;
	}

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(minPrime);
		}
	}

	public static void main(String[] args) {
		PrimeRun p = new PrimeRun(2);
		new Thread(p).start();
	}
}
