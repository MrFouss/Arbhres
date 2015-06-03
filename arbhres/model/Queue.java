package arbhres.model;

import java.util.*;

public class Queue {
    private java.util.Queue<Tile> queue;

    public Queue() {
	for (int i = 0; i < 3; i++) {
	    Tile tile = new Tile(randomTile());
	    this.queue.add(tile);
	}
    }

    public int randomTile() {
	Random rnd = new Random();
	return rnd.nextInt(2) + 1;
    }

    public java.util.Queue<Tile> getQueue() {
	return queue;
    }

}
