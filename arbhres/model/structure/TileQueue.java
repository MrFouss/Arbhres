package arbhres.model.structure;

import java.util.*;

import arbhres.model.Model;

/**
 * @author	Pierre Brunet "pierre.brunet@krophil.fr"
 * @version	1.0
 * @since	05/21/2015
 * 
 * Implements a queue ("Last-In, First-Out", using
 * the "offer", "peek" and "poll" methods) of tiles
 */
public class TileQueue {
	private Queue<Integer> queue;

	/**
	 * Queue constructor with no parameters
	 */
	public TileQueue(Model model) {
		this.queue = new LinkedList<Integer>(); // new empty queue

		int value;
		// initialize the queue with three tiles
		for (int i=0; i<3; i++) {
			value = Grid.randomTile();
			this.offer(value);
			model.fireAddTile(19 - i, value);
		}
	}
	
	public void offer(int integer) {
		System.out.println(integer);
		this.queue.offer(integer);
	}
	
	public int peek() {
		return this.queue.peek();
	}
	
	public int poll() {
		for(int i = 0; i < 3; i++) {
			System.out.println(this.queue.peek());
			this.queue.offer(this.queue.poll());
		}
		return this.queue.poll();
	}

	/**
	 * Queue getter
	 * 
	 * @return the queue object
	 */
	public Queue<Integer> getQueue() {
		return queue;
	}
}
