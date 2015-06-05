package arbhres.model.modifiers;

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
