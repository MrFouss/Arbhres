package arbhres.model.modifiers;

import java.util.Random;

import arbhres.model.structure.Grid;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class RandomModifier extends Modifier {
	private Grid grid;
	/**
	 * Create the modifier without price, which is generated each time the 
	 */
	public RandomModifier(Grid grid) {
		super(0);
		this.grid = grid;
	}
	
	public long apply(long score) {
		Random rnd = new Random();
		if (rnd.nextBoolean()) { //1 chance over two to have a malus
			//maluses
			switch (rnd.nextInt(4)) {
			case 0:
				//TODO Target
				break;
			case 1:
				//TODO Blind
				break;
			case 2:
				TurnLeft turnLeft = new TurnLeft(grid);
				turnLeft.apply(rnd.nextInt(16));
				break;
			case 3:
				TurnRight turnRight = new TurnRight(grid);
				turnRight.apply(rnd.nextInt(16));
				break;
				default:
					return score;
			}
		} else {
			//bonuses
			switch (rnd.nextInt(1)) {
			case 0:
				Pause pause = new Pause(grid.getQueue());
				pause.apply();
				break;
			case 1:
				//TODO See
				break;
			case 2:
				//TODO TurnLeft
				break;
			case 3:
				//TODO TurnRight
				break;
			}
		}
		
		
		return this.updateScore(score);
	}
	
	/**
	 * Generate a random price change the price, with a minimum value of 0
	 * 
	 * @param score The actual score of the game
	 * @return The updated score
	 */
	protected long updateScore(long score) {
		Random rnd = new Random();
		long price;
		
		if (rnd.nextBoolean()) {
			price = rnd.nextInt(500);
		} else {
			if (rnd.nextInt(10) <= 70) {
				price = rnd.nextInt(1500) + 500;
			} else {
				price = rnd.nextInt(8000) + 2000;
			}
		}
		return Math.max(score - price, 0);
	}
}
