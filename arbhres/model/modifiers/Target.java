package arbhres.model.modifiers;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class Target extends Modifier{
	
	/**
	 * Create the modifier with its defined price
	 */
	public Target() {
		super(-8000);
	}
	
	/**
	 * Print a target on a random tile, which will be erased the next turn
	 * 
	 * @return The earned points
	 */
	public long apply() {
		
		//TODO see with Maxime
		
		return updateScore();
	}

}
