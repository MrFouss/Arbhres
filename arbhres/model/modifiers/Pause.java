package arbhres.model.modifiers;

import arbhres.model.structure.TileQueue;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class Pause extends Modifier {
	
	private TileQueue queue;
	
	/**
	 * Create the modifier with its defined price
	 * 
	 * @param queue The next tiles to appear
	 */
	public Pause(TileQueue queue) {
		super(10000);
		this.queue = queue;
	}
	
	/**
	 * Prevents new tiles from being added to the grid for three turns
	 * 
	 * @return the price
	 */
	public long apply() {
		for (int i=0; i<3; i++) {
			queue.poll();
			queue.offer(-1);
		}
		
		return updateScore();
	}
	
	/**
	 * Update the score according to the price of the modifier
	 * 
	 * @param score The actual score
	 * @return the price
	 */
	public boolean isAvailable(long score) {
		return (super.isAvailable(score) && queue.peek() != -1);
	}
}
