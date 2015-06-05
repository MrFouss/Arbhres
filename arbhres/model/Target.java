package arbhres.model;

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
