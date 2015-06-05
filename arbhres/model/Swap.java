package arbhres.model;

public class Swap extends Modifier {

	public Swap() {
		super(8000);
	}
	
	public long apply(long score, Grid grid, int indexTile1, int indexTile2) {
		if (indexTile1 != indexTile2) {
			//TODO change when getTile is implemented
			int value1 = (indexTile1 == 16 ? grid.getInventory() : 0/*grid.getTile(indexTile1)*/);
			int value2 = (indexTile2 == 16 ? grid.getInventory() : 0/*grid.getTile(indexTile2)*/);
			
			//TODO change when setInventory is implemented
			if (indexTile1 == 16) {
				/*grid.setInventory*/
			} else {
				grid.addTile(indexTile1, value2); 
			}
			
			if (indexTile2 == 16) {
				/*grid.setInventory*/
			} else {
				grid.addTile(indexTile2, value1); 
			}
			
			return updateScore(score);
		}
		return score;
	}
	
	public boolean isAvailable(long score, Grid grid) {
		//TODO add when isEmptyGrid is implemented
		return (super.isAvailable(score) /*&& grid.isEmptyGrid()*/);
	}

}
