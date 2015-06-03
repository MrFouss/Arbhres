package arbhres.model;

import java.util.Random;

public class Grid {
    private Tile[] tiles;
    private Queue queue;
    private Tile inventory;

    public Grid() {
	this.tiles = new Tile[16];
    }

    public Grid(Tile[] tiles) {
	this.tiles = tiles;
    }

    public Grid(Grid grid) {
	this.tiles = grid.tiles;
	this.queue = grid.queue;
    }

    public void addFirst(Tile tile) {
	int i = 0;
	Random rnd = new Random();
	do {
	    add(rnd.nextInt(3) + 1, tile);
	} while (i < 9);
    }

    public void add(int coord, Tile tile) {
	this.tiles[coord] = tile;
    }

    private int randomTile() {
	Random rnd = new Random();
	return rnd.nextInt(2) + 1;

    }

    private int selectAnySide() {
	Random rnd = new Random();
	return 3 + (rnd.nextInt(3)) * 4;
    }

    private void left(Tile tile, int side) {
	Tile newTile = new Tile(queue.getQueue().poll());
	add(selectAnySide(), newTile);
    }

    public void rotate(int side) {
	Tile[] newTiles = new Tile[16];
	int angle;
	switch (side) {
	case 1:
	    angle = 90;
	    break;
	case 2:
	    angle = 180;
	    break;
	case 3:
	    angle = 270;
	    break;
	}

    }

}
