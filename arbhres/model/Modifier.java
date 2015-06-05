package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/04/2015
 */
public abstract class Modifier {
	
	/**
	 * Points lost if you use a bonus or earned if you survive
	 */
	private long price;
	
	public Modifier(long price) {
		this.price = price;
	}
	
	/**
	 * Checks if a modifier can be used
	 * @param score The actual score
	 * @return true if the modifier can be used, false otherwise
	 */
	public boolean isAvailable(long score) {
		return (score >= this.price);
	}
	
	/**
	 * Update the score according to the price of the modifier
	 * @param score The actual score
	 * @return The updated score
	 */
	protected long updateScore(long score) {
		return (score - this.price);
	}
}
