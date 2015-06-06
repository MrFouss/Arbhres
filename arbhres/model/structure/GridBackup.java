package arbhres.model.structure;

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
