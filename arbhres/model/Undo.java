package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	05/21/2015
 */
public class Undo extends Modifier {
	
	public Undo(){
		this.price = 5000;
	}
	
	/**
	 * 
	 * @param score The actual score
	 * @param grid The main grid
	 * @param backup The stored grid
	 * @return
	 */
	public long apply(long score, Grid grid, Grid backup) {
		//TODO grid.emptyGrid(); the emptyGrid grid method needs to be implemented
		grid = backup;
		backup = null;
		
		return updateScore(score);
	}
	
	public boolean isAvailable(long score, Grid backup) {
		return (super.isAvailable(score) && backup != null);
	}
}
