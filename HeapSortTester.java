import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.Test;

class HeapSortTester<E> {
	
	@Test
	void test() {
		Integer[] array = {4,6, 5, 8, 7, 7, 9, 9};
		PQueueHeap<Integer> queue = new PQueueHeap<>();

		for(int i : array)
			queue.offer(i);
		
		queue.poll();
 
        // Displaying the PriorityQueue
        System.out.println("Initial PriorityQueue: " + queue.toString() + "\n");
        
        queue.add(1);

        System.out.println("Initial PriorityQueue: " + queue.toString() + "\n");

        queue.add(3);

        System.out.println("Initial PriorityQueue: " + queue.toString() + "\n");
        
        queue.poll();
		
        System.out.println("Initial PriorityQueue: " + queue.toString() + "\n");

		
		
		}
}


