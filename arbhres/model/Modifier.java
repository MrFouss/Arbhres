package arbhres.model;

/**
 * @author Stéphane Perrez <stephane.perrez@utbm.fr>
 * @version	1.0
 * @since	05/21/2015
 */
public abstract class Modifier {
	/**
	 * Points lost if you use a bonus or earned if you survive
	 */
	protected int price;
	
	public Modifier() {
		
	}
	
	protected int getPrice() {
		return this.price;
	}
	
	public boolean isAvailable(long score) {
		return (score >= this.price);
	}
	
	protected long updateScore(long score) {
		return (score - this.price);
	}
}
