package arbhres.model.structure;

import java.util.Random;

import arbhres.controller.events.MovementEvent.Direction;

/**
 * @author	Pierre Brunet "pierre.brunet@krophil.fr"
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
	 * Inventory tile setter
	 * 
	 * @param inventory The new value to set
	 */
	public void setInventory(int inventory) {
		this.inventory=inventory;
	}
	
	/**
	 * Return the value of the selected tile
	 * 
	 * @param tileIndex The index of the tile you want
	 * @return the value contained in the tile
	 */
	public int getTile(int tileIndex) {
		return this.tiles[tileIndex];
	}
	
	/**
	 * Reinitialize the reference to the tiles
	 * 
	 */
	public void emptyGrid(){
		this.tiles = null;
	}
	/**
	 * Initialize the tile array with 9 random tiles between 1 and 3
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
	 * Reset the tile to -1
	 * 
	 * @param tileIndex Index of the tile spot to remove
	 */
	public void removeTile(int tileIndex){
		if(tileIndex==16){
			this.inventory=-1;
		} else {
			this.tiles[tileIndex]=-1;
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
	 * Check if the grid is empty
	 * 
	 * @return true if the grid is empty
	 */
	public boolean isGridEmpty(){
		boolean isEmpty=true;
		for(int i=0; i<16; i++){
			if(!isTileEmpty(i)){
				isEmpty=false;
			}
		}
		return isEmpty;
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
	 * Returns the highest tile
	 * 
	 * @return the index of the highest tile
	 */
	public int selectHighest() {
		int highest=0;
		for(int i=0;i<16;i++) {
			if (tiles[i]>tiles[highest]) {
				highest=i;
			}
		}
		return highest;
	}
	
	/**
	 * counterclockwise rotation of one of the 4 areas
	 * @param area The index of the area (from 0 to 3)
	 */
	public void rotateLeft(int area){
		int tmp;
		int start = 0;
		switch (area) {
		case 1:
			start = 2;
			break;
		case 2:
			start = 8;
			break;
		case 3:
			start = 10;
			break;
		}
		tmp=tiles[start];
		this.addTile(start, tiles[start+1]);
		this.addTile(start+1, tiles[start+5]);
		this.addTile(start+5, tiles[start+4]);
		this.addTile(start+4, tmp);
	}
	
	/**
	 * counterclockwise rotation of one of the 4 areas
	 * @param area he index of the area (from 0 to 3)
	 */
	public void rotateRight(int area){
		int tmp;
		int start = 0;
		switch (area) {
		case 1:
			start = 2;
			break;
		case 2:
			start = 8;
			break;
		case 3:
			start = 10;
			break;
		}
		tmp=tiles[start];
		tiles[start]=tiles[start+4];
		tiles[start+4]=tiles[start+5];
		tiles[start+5]=tiles[start+1];
		tiles[start+1]=tmp;
	}
	
	/**
	 * Check if two tiles can be merged
	 * 
	 * @param i first tile
	 * @param j second tile
	 * @return if tiles can be merged
	 */
	private boolean isMergeable(int i, int j) {
		return ((tiles[i]==tiles[j] && tiles[i] != 1 && tiles[i] !=2 && tiles[i]!=-1) || (tiles[i]==1 && tiles[j]==2) || (tiles[j]==1 && tiles[i]==2));
	}
	

	/**
	 * Moves the grid in a direction
	 * 
	 * @param direction The direction where the tiles are going
	 * @param backup The backup grid
	 * @param score The score to change
	 */
	public void move(Direction direction, GridBackup backup, long score) {

		GridBackup tmpBackup = new GridBackup(this, score);
		
		switch (direction) {
		case DOWN:
			rotate(1);
			break;
		case RIGHT:
			rotate(2);
			break;
		case UP:
			rotate(3);
			break;
		default:
			break;
		}
		
		boolean hasMoved=false;
		for(int j=0; j<4; j++) {
			for(int i=0; i<3; i++) {
				if(isMergeable(i+j*4, i+1+j*4)) {
					tiles[i+j*4]+=tiles[i+1+j*4]; // merges Tile[i] and Tile[i+1]
					tiles[i+1+j*4]=-1;
					score += tiles[i+j*4]*100;
					hasMoved=true;
				} else {
					if(isTileEmpty(i+j*4)){ // checks if the tile is empty
						tiles[i+j*4]=tiles[i+1+j*4]; // moves the previous tile to the empty one
						tiles[i+1+j*4]=-1; // empty the previous tile
						hasMoved=true;
					} 
				}
			}
		}
		if(hasMoved || this.isGridEmpty()) {
			addTile(selectAnySide(), queue.getQueue().poll());
			queue.getQueue().offer(randomTile());
			backup = tmpBackup;
		}		
		
		switch (direction) {
		case DOWN:
			rotate(3);
			break;
		case RIGHT:
			rotate(2);
			break;
		case UP:
			rotate(1);
			break;
		default:
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
	
	public void copyGrid(Grid grid) {
		this.inventory = grid.inventory;
		this.tiles = grid.tiles.clone();
		for(int i = 0; i < 3; i++) {
			this.queue.offer(grid.queue.peek());
			grid.queue.offer(grid.queue.poll());
		}
	}
}
