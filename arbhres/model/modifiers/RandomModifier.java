package arbhres.model.modifiers;

import java.util.Random;

import arbhres.model.structure.Grid;

/**
 * @author Stéphane Perrez "stephane.perrez@utbm.fr"
 * @version	1.0
 * @since	06/05/2015
 */
public class RandomModifier {
	private Grid grid;
	private boolean clickTile;
	private int seeTurns;
	private int blindTurns;
	private int targetIndex;
	private boolean eraseBool;
	private boolean lTurnBool;
	private boolean rTurnBool;
	private int swapStep;
	
	/**
	 * Create the modifier without price, which is generated each time the bonus is used
	 * 
	 * @param grid The main grid
	 */
	public RandomModifier(Grid grid) {
		this.grid = grid;
		this.seeTurns = 0;
		this.blindTurns = 0;
		this.targetIndex = -1;
		this.eraseBool = false;
		this.seeTurns = 0;
		this.lTurnBool = false;
		this.rTurnBool = false;
		this.swapStep = 0;
	}
	
	public long apply(long score) {
		Random rnd = new Random();
		this.eraseBool = false;
		this.lTurnBool = false;
		this.rTurnBool = false;
		this.clickTile = false;
		
		if (rnd.nextBoolean()) { //1 chance over 2 to have a malus
			//maluses
			switch (rnd.nextInt(4)) {
			case 0:
				System.out.println("target");
				Target target = new Target(this.grid, this.targetIndex );
				if(target.isAvailable()) {
					score-= target.apply();
					this.targetIndex = target.getIndex();
				}
				break;
			case 1:
				Blind blind = new Blind();
				score-=blind.apply();
				this.blindTurns = blind.getBlindTurns();
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
					this.eraseBool = true;
					this.clickTile = true;
					price = erase.updateScore();
				}
				break;
			case 1:
				See see = new See();
				see.apply();
				this.seeTurns += see.getSeeTurns();
				break;
			case 2:
				Pause pause = new Pause(grid.getQueue());
				pause.apply();
				break;
			case 3:
				TurnLeft turnLeft = new TurnLeft(grid);
				
				this.clickTile = true;
				this.lTurnBool = true;

				price = turnLeft.updateScore();
				break;
			case 4:
				TurnRight turnRight = new TurnRight(grid);
				
				this.clickTile = true;
				this.rTurnBool = true;

				price = turnRight.updateScore();
				break;
			case 5:
				Swap swap = new Swap(grid);
				
				if (swap.isAvailable(swap.updateScore()) ) {
					
					this.clickTile = true;
					this.swapStep = 1;
					
					price = swap.updateScore();
				}
				break;
				default:
					return score;
			}
			return this.updateScore(score) + price;
		}
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

	public boolean getEraseBool() {
		return this.eraseBool;
	}
	public boolean getlTurnBool() {
		return lTurnBool;
	}

	public boolean getrTurnBool() {
		return rTurnBool;
	}

	public boolean getClickTile() {
		return clickTile;
	}
	
	public int getSwapStep() {
		return this.swapStep;
	}
}
