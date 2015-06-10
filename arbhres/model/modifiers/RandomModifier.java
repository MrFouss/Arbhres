package arbhres.model.modifiers;

import java.util.Random;

import arbhres.controller.listeners.ControllerListener;
import arbhres.events.ButtonClickEvent;
import arbhres.events.MovementEvent;
import arbhres.events.TileClickEvent;
import arbhres.model.structure.Grid;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class RandomModifier implements ControllerListener{
	private Grid grid;
	private boolean clickTile;
	private int tileIndex;
	private int blindTurn;
	
	/**
	 * Create the modifier without price, which is generated each time the bonus is used
	 * 
	 * @param grid The main grid
	 */
	public RandomModifier(Grid grid) {
		this.grid = grid;
	}
	
	public long apply(long score) {
		Random rnd = new Random();
		if (rnd.nextBoolean()) { //1 chance over two to have a malus
			//maluses
			switch (rnd.nextInt(4)) {
			case 0:
				//TODO Target
				break;
			case 1:
				// TODO Blind
				break;
			case 2:
				TurnLeft turnLeft = new TurnLeft(grid);
				turnLeft.apply(rnd.nextInt(16));
				break;
			case 3:
				TurnRight turnRight = new TurnRight(grid);
				turnRight.apply(rnd.nextInt(16));
				break;
			}
			return score;
		} else {
			//bonuses
			switch (rnd.nextInt(6)) {
			case 0:
				Erase erase = new Erase(grid);
				if (erase.isAvailable(erase.updateScore()) ) {
					this.clickTile = true;
					while (this.clickTile) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							
						}
					}
					erase.apply(this.tileIndex);
				}
				break;
			case 1:
				//TODO See
				break;
			case 2:
				Pause pause = new Pause(grid.getQueue());
				pause.apply();
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
				turnLeft.apply(this.tileIndex);
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
				turnRight.apply(this.tileIndex);
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

	@Override
	public void buttonClicked(ButtonClickEvent e) {
	}

	@Override
	public void gridMoved(MovementEvent e) {	
	}

	@Override
	public void tileClicked(TileClickEvent e) {
		if (this.clickTile) {
			this.tileIndex = e.getTileIndex();
			this.clickTile = false;
		}
		
	}
}
