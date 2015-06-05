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
	 * @param score
	 * @param queue
	 * @return
	 */
	public long apply(long score, TileQueue queue) {
		
		//TODO add when TileQueue is done
		
		return updateScore(score);
	}
	
	public boolean isAvailable(long score, int tile) {
		
		return (super.isAvailable(score) && tile != -1);
	}
	
	
	public boolean isAvailable(long score, TileQueue queue) {
		//TODO see when TileQueue is done
		return (super.isAvailable(score) && queue.getQueue().element() != -1);
	}
}
