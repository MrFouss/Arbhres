package arbhres.events;

public class ScoreChangeEvent {
	
	private long score;
	
	public ScoreChangeEvent(long score) {
		this.score = score;
	}

	public long getScore() {
		return this.score;
	}
}
