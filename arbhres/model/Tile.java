package arbhres.model;

/**
 * @author	Pierre Brunet <pierre.brunet@krophil.fr>
 * @version	1.0
 * @since	05/21/2015
 */
public class Tile {
	int data;

	public Tile() {
		this.data = 0;
	}

	public Tile(int data) {
		this.data = data;
	}

	public Tile(Tile tile) {
		this.data = tile.data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getData() {
		return this.data;
	}
}
