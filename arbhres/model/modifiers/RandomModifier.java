package arbhres.model.modifiers;

import java.util.Random;

import arbhres.events.ButtonClickEvent;
import arbhres.events.MovementEvent;
import arbhres.events.TileClickEvent;
import arbhres.controller.listeners.ControllerListener;
import arbhres.model.structure.Grid;
import arbhres.model.Model;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class RandomModifier {
	private Grid grid;
	private Model model;
	private boolean clickTile;
	private int tileIndex;
	private int seeTurns;
	private int blindTurns;
	private int targetIndex;
	
	/**
	 * Create the modifier without price, which is generated each time the bonus is used
	 * 
	 * @param grid The main grid
	 */
	public RandomModifier(Grid grid, Model model) {
		this.grid = grid;
		this.seeTurns = 0;
		this.blindTurns = 0;
		this.targetIndex = -1;
		this.model = model;
	}
	
	public long apply(long score) {
		Random rnd = new Random();
		if (/*rnd.nextBoolean()*/true) { //1 chance over 2 to have a malus
			//maluses
			switch (/*rnd.nextInt(4)*/1) {
			case 0:
				System.out.println("target");
				Target target = new Target(this.grid, this.targetIndex );
				if(target.isAvailable()) {
					score-= target.apply();
					this.targetIndex = target.getIndex();
				}
				break;
			case 1:
				System.out.println("blind");
				Blind blind = new Blind();
				score-=blind.apply();
				blindTurns += blind.getBlindTurns();
				break;
			case 2:
				System.out.println("rlturn");
				TurnLeft turnLeft = new TurnLeft(grid);
				score+=turnLeft.apply(rnd.nextInt(16));
				break;
			case 3:
				System.out.println("rrturn");
				TurnRight turnRight = new TurnRight(grid);
				score+=turnRight.apply(rnd.nextInt(16));
				break;
			}
			return score;
		} else {
			//bonuses
			long price = 0;
			switch (rnd.nextInt(6)) {
			case 0:
				Erase erase = new Erase(grid);
				if (erase.isAvailable(erase.updateScore()) ) {
					//this.eraseBool = true;
					this.clickTile = true;

					price = erase.apply(this.tileIndex);
				}
				break;
			case 1:
				See see = new See();
				price = see.apply();
				this.seeTurns += see.getSeeTurns();
				break;
			case 2:
				Pause pause = new Pause(grid.getQueue());
				price = pause.apply();
				break;
			case 3:
				TurnLeft turnLeft = new TurnLeft(grid);

				this.clickTile = true;
				while (this.clickTile) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						
					}
				}
				price = turnLeft.apply(this.tileIndex);
				break;
			case 4:
				TurnRight turnRight = new TurnRight(grid);
				
				this.clickTile = true;
				while (this.clickTile) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						
					}
				}
				price = turnRight.apply(this.tileIndex);
				break;
			case 5:
				Swap swap = new Swap(grid);
				int tileIndex1, tileIndex2;
				
				if (swap.isAvailable(swap.updateScore()) ) {
					this.clickTile = true;
					while (this.clickTile) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							
						}
					}
					tileIndex1 = this.tileIndex;
					this.clickTile = true;
					while (this.clickTile) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							
						}
					}
					tileIndex2 = this.tileIndex;
					swap.apply(tileIndex1, tileIndex2);
				}
				default:
					return score;
			}
		}
		
		
		return this.updateScore(score);
	}
	
	/**
	 * Generate a random price change the price, with a minimum value of 0
	 * 
	 * @param score The actual score of the game
	 * @return The updated score
	 */
	protected long updateScore(long score) {
		Random rnd = new Random();
		long price;
		
		if (rnd.nextBoolean()) {
			price = rnd.nextInt(500);
		} else {
			if (rnd.nextInt(10) <= 70) {
				price = rnd.nextInt(1500) + 500;
			} else {
				price = rnd.nextInt(8000) + 2000;
			}
		}
		return Math.max(score - price, 0);
	}



	public int getSeeTurns() {
		return seeTurns;
	}

	public void setSeeTurns(int seeTurns) {
		this.seeTurns = seeTurns;
	}

	public int getBlindTurns() {
		return blindTurns;
	}

	public void setBlindTurns(int blindTurns) {
		this.blindTurns = blindTurns;
	}

	public int getTargetIndex() {
		return targetIndex;
	}

	public void setTargetIndex(int targetIndex) {
		this.targetIndex = targetIndex;
	}


}
