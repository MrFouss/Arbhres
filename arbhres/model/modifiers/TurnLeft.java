package arbhres.model.modifiers;

import arbhres.model.structure.Grid;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class TurnLeft extends Modifier {
	
	private Grid grid;
	
	/**
	 * Create the modifier with its defined price
	 * 
	 * @param grid The main grid
	 */
	public TurnLeft(Grid grid) {
		super(4000);
		this.grid = grid;
	}
	
	/**
	 * Make a counterclockwise rotation of a group of 4 tiles
	 * 
	 * @param tileIndex The index of one of the 4 tiles to rotate
	 * @return the price
	 */
	public long apply(int tileIndex) {
		int area;

		switch (tileIndex) {
		case 0: case 1: case 4: case 5:
			area = 0;
			break;
		case 2: case 3: case 6: case 7:
			area = 1;
			break;
		case 8: case 9: case 12: case 13:
			area = 2;
			break;
		case 10: case 11: case 14: case 15:
			area = 3;
			break;
			default:
				return 0;
		}

		grid.rotateLeft(area);
		return updateScore();
	}
}
