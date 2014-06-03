package test.internal;

import java.util.ArrayList;
import java.util.List;

public class ShareClosure {
	List<Action> list = new ArrayList<Action>();

	public void Input() {
		for (int i = 0; i < 10; i++) {
			final int copy = i;
			list.add(new Action() {
				@Override
				public void run() {
					System.out.println(copy);
				}
			});
		}
	}

	public void Output() {
		for (Action a : list) {
			a.run();
		}
	}

	public static void main(String[] args) {
		ShareClosure sc = new ShareClosure();
		sc.Input();
		sc.Output();
	}
}