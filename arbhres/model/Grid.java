package arbhres.model;

import java.util.Random;

/**
 * @author	Pierre Brunet <pierre.brunet@krophil.fr>
 * @version	1.0
 * @since	05/21/2015
 */
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
			addTile(rnd.nextInt(3) + 1, tile);
		} while (i < 9);
	}

	public void addTile(int coord, Tile tile) {
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
		addTile(selectAnySide(), newTile);
	}

	public void rotate(int side) {
		Tile[] newTiles = new Tile[16];
		int angle;
		int oldX = 0;
		int oldY = 0;
		switch (side) {
		case 1:
			for(int i=0; i<16; i++) {
				oldX=tiles[i].getData()%4;
				oldY=tiles[i].getData()/4;
				newTiles[3-oldY+(4*oldX)]=tiles[i];
				this.tiles=newTiles;
			}
			break;
		case 2:
			for(int i=0; i<16; i++) {
				oldX=tiles[i].getData()%4;
				oldY=tiles[i].getData()/4;
				newTiles[oldY+4*(3-oldX)]=tiles[i];
				this.tiles=newTiles;
			}
			break;
		case 3:
			for(int i=0; i<16; i++) {
				oldX=tiles[i].getData()%4;
				oldY=tiles[i].getData()/4;
				newTiles[3-oldX+4*(3-oldY)]=tiles[i];
				this.tiles=newTiles;
			}
			break;
		}

	}
}
