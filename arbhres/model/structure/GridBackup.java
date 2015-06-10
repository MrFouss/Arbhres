package arbhres.model.structure;

import arbhres.model.Model;

/**
 * @author	Pierre Brunet "pierre.brunet@krophil.fr"
 * @version	1.0
 * @since	05/21/2015
 */
public class GridBackup extends Grid{
	
	private long score;
	
	public GridBackup(Grid grid, long score, Model model) {
		super(model);
		this.score = score;
	}

	public long getScore() {
		return score;
	}
	
	
}
