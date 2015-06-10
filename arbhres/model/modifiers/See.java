package arbhres.model.modifiers;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class See extends Modifier {
	
	private int seeTurn;
	
	/**
	 * Create the modifier with its defined price
	 */
	public See() {
		super(4000);
		this.seeTurn = 3;
	}
	
	/**
	 * Make the three next tiles of the queue visible
	 * 
	 * @return the price
	 */
	public long apply() {
		return updateScore();
	}
	
	/**
	 * Getter for the amount of turns left
	 * 
	 * @return the number of rounds left before the "see" bonus stops
	 */
	public int getSeeTurns() {
		return this.seeTurn;
	}
	
	/**
	 * Checks if a modifier can be used
	 * 
	 * @param score The actual score
	 * @return true if the modifier can be used, false otherwise
	 */
	public boolean isAvailable(long score) {
		return super.isAvailable(score);
	}

}
