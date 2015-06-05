package arbhres.model.modifiers;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class Blind extends Modifier {
	
	/**
	 * Create the modifier with its defined price
	 */
	public Blind() {
		super(-5000);
	}
	
	/**
	 * Conceal the value on the tiles during 3 turns
	 * 
	 * @return The earned points if you survive
	 */
	public long apply() {
		
		//TODO see with Esia and Maxime
		
		return updateScore();
	}
}
