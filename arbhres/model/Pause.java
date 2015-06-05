package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	06/05/2015
 */
public class Pause extends Modifier {
	
	public Pause() {
		super(10000);
	}
	
	/**
	 * Prevents new tiles from being added to the grid for three turns
	 * 
	 * @param score The actual score
	 * @param queue The queue of the main Grid
	 * @return the price
	 */
	public long apply(TileQueue queue) {
		
		//TODO add when TileQueue is done
		
		return updateScore();
	}
	
	/**
	 * Update the score according to the price of the modifier
	 * 
	 * @param score The actual score
	 * @param tile  The head of queue
	 * @return the price
	 */
	public boolean isAvailable(long score, int tile) {
		
		return (super.isAvailable(score) && tile != -1);
	}
	

	/**
	 * Checks if a modifier can be used
	 * 
	 * @param score The actual score
	 * @param queue The queue of the main Grid
	 * @return true if the modifier can be used, false otherwise
	 */
	public boolean isAvailable(long score, TileQueue queue) {
		//TODO see when TileQueue is done
		return (super.isAvailable(score) && queue.getQueue().element() != -1);
	}
}
