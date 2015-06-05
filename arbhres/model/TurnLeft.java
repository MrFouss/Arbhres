package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class TurnLeft extends Modifier {

	public TurnLeft() {
		super(4000);
	}
	
	public long apply(long score, int area, Grid grid) {

		//TODO Ask about rotate function in grid
		
		return updateScore(score);
	}
}
