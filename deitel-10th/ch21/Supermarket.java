// Ex 21.15: Supermarket simulation.

import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

public final class Supermarket {
	public static void main(String[] args){
		final Random r = new Random();
		Queue<Integer> queue = new LinkedList<>();
		int arrival = 1+r.nextInt(4);
		int service = arrival+1+r.nextInt(4);
		int maxQueue = 0;
		int maxWait  = 0;
		for (int time = 1; time <= 720; time++) {
			if (time == arrival) {
				System.out.println("Arrived "+time);
				queue.offer(time);
				arrival += 1 + r.nextInt(4);
				if (queue.size() > maxQueue)
					maxQueue = queue.size();
			}

			if (time == service) {
				if (queue.size() > 0) {
					System.out.println("Serviced "+time);
					final int wait = time - queue.poll();
					if (wait > maxWait)
						maxWait = wait;
					service += 1+r.nextInt(4);
				} else {
					service = arrival+1+r.nextInt(4);
				}
			}
		}

		String four = String.format("Stats: %n%-12s %d%n%-12s %d%n",
							"Max Q Size:", maxQueue, "Max Wait: ", maxWait);

		// 1 to 3 minutes
		queue = new LinkedList<>();
		arrival = 1+r.nextInt(3);
		service = arrival+1+r.nextInt(3);
		maxQueue = 0;
		maxWait  = 0;
		for (int time = 1; time <= 720; time++) {
			if (time == arrival) {
				System.out.println("Arrived "+time);
				queue.offer(time);
				arrival += 1 + r.nextInt(3);
				if (queue.size() > maxQueue)
					maxQueue = queue.size();
			}

			if (time == service) {
				if (queue.size() > 0) {
					System.out.println("Serviced "+time);
					final int wait = time - queue.poll();
					if (wait > maxWait)
						maxWait = wait;
					service += 1+r.nextInt(3);
				} else {
					service = arrival+1+r.nextInt(3);
				}
			}
		}
		System.out.println("For 4:\n"+four);
		System.out.println("For 3:\n");
		System.out.printf("Stats: %n%-12s %d%n%-12s %d%n",
							"Max Q Size:", maxQueue, "Max Wait: ", maxWait);
	}
}
