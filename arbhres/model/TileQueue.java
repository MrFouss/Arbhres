package arbhres.model;

import java.util.*;

/**
 * @author	Pierre Brunet <pierre.brunet@krophil.fr>
 * @version	1.0
 * @since	05/21/2015
 */
public class TileQueue {
	private Queue<Integer> queue;

	public TileQueue() {
		this.queue = new LinkedList<Integer>();
		for (int i=0; i<3; i++) {
			this.queue.offer(randomTile());
		}
	}

	public int randomTile() {
		Random rnd = new Random();
		return rnd.nextInt(3) + 1;
	}

	public Queue<Integer> getQueue() {
		return queue;
	}
}
