package com.thread.test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Strategy4 {

	public static void main(String[] args) {
		List<Task> tasks = TaskProducer.produce(1000);
		handleTasks(tasks, 10);
		System.out.println("All finished");
	}

	public static void handleTasks(List<Task> tasks, int threadCount) {
		try {
			ExecutorService executor = Executors.newFixedThreadPool(threadCount);

			for (Task task : tasks) {
				executor.submit(new TaskHandler(task));
			}

			executor.shutdown();
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static class TaskHandler implements Runnable {

		private Task task;

		public TaskHandler(Task task) {
			this.task = task;
		}

		public void run() {
			task.start();
		}
	}
}
