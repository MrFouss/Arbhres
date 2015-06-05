package arbhres.model;

public class See extends Modifier {
	
	public See() {
		super(4000);
	}
	
	public long apply(long score) {
		
		//TODO see with Maxime
		
		return updateScore(score);
	}
	
	public boolean isAvailable(long score) {
		
		//TODO see with Maxime
		
		return super.isAvailable(score);
	}

}
