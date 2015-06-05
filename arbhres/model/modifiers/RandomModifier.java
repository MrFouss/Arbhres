package arbhres.model.modifiers;

import java.util.Random;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class RandomModifier extends Modifier {
	
	/**
	 * Create the modifier without price, which is generated each time the 
	 */
	public RandomModifier() {
		super(0);
	}
	
	//TODO implement the rest of the methods
	
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
