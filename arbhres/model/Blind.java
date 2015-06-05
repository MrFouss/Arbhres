package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class Blind extends Modifier {
	
	public Blind() {
		super(-5000);
	}

	public long apply(long score) {
		
		//TODO see with Esia and Maxime
		
		return updateScore(score);
	}
}
