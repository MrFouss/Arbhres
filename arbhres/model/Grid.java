package arbhres.model;

import java.util.Random;

import arbhres.controller.events.MovementEvent.Direction;

/**
 * @author	Pierre Brunet <pierre.brunet@krophil.fr>
 * @version	1.0
 * @since	05/21/2015
 * 
 * Class implementing grids (a tile array, an inventory tile and a tile queue)
 */
public class Grid {
	private int[] tiles;
	private TileQueue queue;
	private int inventory;

	/**
	 * Grid constructor with no parameters
	 * 
	 * Simply initialize the tiles array (with empty tiles),
	 * the queue with 3 tiles in it and the inventory with an empty tile.
	 */
	public Grid() {
		this.tiles = new int[16]; // new tile array
		this.queue = new TileQueue(); // new queue
		this.inventory = -1; // new inventory tile
		
		// initialize the tiles array with empty tiles
		for(int i=0; i<16; i++) {
			this.tiles[i] = -1;
		}
	}

	/**
	 * Grid constructor with an existing tile array parameter
	 * 
	 * Initialize the tiles array with an existing one and initialize
	 * the queue and the inventory with the default settings
	 * 
	 * @param tiles existing tiles array
	 */
	public Grid(int[] tiles) {
		this.tiles = tiles; // existing tile array
		this.queue = new TileQueue(); // new queue
		this.inventory = -1; // new inventory tile
	}

	/**
	 * Grid constructor with an existing grid object
	 * 
	 * @param grid existing grid object
	 */
	public Grid(Grid grid) {
		this.tiles = grid.tiles; // existing tile array
		this.queue = grid.queue; // existing queue
		this.inventory = grid.inventory; // existing inventory tile
	}
	
	/**
	 * Tile array getter
	 * 
	 * @return the array containing the tiles' values
	 */
	public int[] getTiles() {
		return this.tiles;
	}
	
	/**
	 * Queue getter
	 * 
	 * @return the queue object
	 */
	public TileQueue getQueue() {
		return this.queue;
	}

	/**
	 * Inventory tile getter
	 * 
	 * @return the value of the inventory tile
	 */
	public int getInventory() {
		return this.inventory;
	}

	/**
	 * Initializes the tile array with 9 random tiles between 1 and 3
	 */
	public void initTiles() {
		Random rnd = new Random();
		
		// while 9 tiles have not been filled
		for (int i=0; i<9; i++) {
			int tileIndex;
			
			// while an empty tile spot has not been found
			do {
				tileIndex = rnd.nextInt(16); // look for another random spot
			} while (!isTileEmpty(tileIndex));
			
			addTile(tileIndex, randomTile());
		}
	}
	
	/**
	 * Generate a random tile value
	 * 
	 * @return an integer between 1 and 3
	 */
	public static int randomTile() {
		Random rnd = new Random();
		return rnd.nextInt(3) + 1;
	}

	/**
	 * Add a new tile to the tile array
	 * 
	 * @param coord the array coordinate (between 0 and 15)
	 * @param value the value of the new tile
	 */
	public void addTile(int coord, int value) {
		this.tiles[coord] = value;
	}
	
	/**
	 * Check if the tile spot is empty
	 * 
	 * @param tileIndex the index of the tile in the tile array
	 * @return true if the tile is empty, false otherwise
	 */
	public boolean isTileEmpty(int tileIndex) {
		return (this.tiles[tileIndex] == -1);
	}
	
	/**
	 * Select an empty tile spot at the right side of the tile array
	 * 
	 * @return the index of the empty tile spot
	 */
	public int selectAnySide() {
		Random rnd = new Random();
		int tileIndex;
		
		// while the selected spot is not empty
		do {
			tileIndex = 3 + (rnd.nextInt(3)) * 4; // look for another empty spot
		} while(!isTileEmpty(tileIndex));
		
		return tileIndex;
	}
	
	/**
	 * Moves the grid in a direction
	 * 
	 * @param direction the direction where the tiles are going
	 */
	public void move(Direction direction) {
		switch (direction) {
		case LEFT:
			// TODO move left (move and merge all tiles, add a new tile with selectAnySide)
			break;
		case DOWN:
			rotate(1);
			move(Direction.LEFT);
			rotate(3);
			break;
		case RIGHT:
			rotate(2);
			move(Direction.LEFT);
			rotate(2);
			break;
		case UP:
			rotate(3);
			move(Direction.LEFT);
			rotate(1);
			break;
		}
	}

	/**
	 * Rotate the tile array
	 * 
	 * @param nbRotations the number of clockwise quarter turns the tile array will be rotated
	 */
	public void rotate(int nbRotations) {
		int[] newTiles = new int[16];
		int X, Y;
		
		switch (nbRotations) {
		case 1:
			for(int i=0; i<16; i++) {
				X=i%4;
				Y=i/4;
				newTiles[3-Y+(4*X)]=tiles[i];
			}
			this.tiles=newTiles;
			break;
		case 2:
			for(int i=0; i<16; i++) {
				X=i%4;
				Y=i/4;
				newTiles[3-X+4*(3-Y)]=tiles[i];
			}
			this.tiles=newTiles;
			break;
		case 3:
			for(int i=0; i<16; i++) {
				X=i%4;
				Y=i/4;
				newTiles[Y+4*(3-X)]=tiles[i];
			}
			this.tiles=newTiles;
			break;
		}
	}
}
