package arbhres.model.modifiers;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class See extends Modifier {
	
	/**
	 * Create the modifier with its defined price
	 */
	public See() {
		super(4000);
	}
	
	/**
	 * Make the three next tiles of the queue visible
	 * 
	 * @return the price
	 */
	public long apply() {
		
		//TODO see with Maxime
		
		return updateScore();
	}
	
	/**
	 * Checks if a modifier can be used
	 * 
	 * @param score The actual score
	 * @return true if the modifier can be used, false otherwise
	 */
	public boolean isAvailable(long score) {
		
		//TODO see with Maxime
		
		return super.isAvailable(score);
	}

}
