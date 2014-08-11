package com.thread.test;

public class Task {
	private int id;

	public Task(int id) {
		this.id = id;
	}

	public void start() {
		System.out.println(Thread.currentThread().getName() + ": start to handle task " + id);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}
}
