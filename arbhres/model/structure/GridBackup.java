package arbhres.model.structure;

/**
 * @author	Pierre Brunet "pierre.brunet@krophil.fr"
 * @version	1.0
 * @since	05/21/2015
 */
public class GridBackup extends Grid{
	
	private long score;
	
	public GridBackup(Grid grid, long score) {
		super();
		this.score = score;
	}

	public long getScore() {
		return score;
	}
	
	
}
