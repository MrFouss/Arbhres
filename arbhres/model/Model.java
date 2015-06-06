package arbhres.model;

import arbhres.controller.events.ButtonClickEvent;
import arbhres.controller.events.MovementEvent;
import arbhres.controller.events.TileClickEvent;
import arbhres.controller.listeners.ControllerListener;
import arbhres.model.modifiers.*;
import arbhres.model.structure.Grid;
import arbhres.model.structure.GridBackup;

/**
 * @author	Pierre Brunet <pierre.brunet@krophil.fr>
 * @version	1.0
 * @since	05/21/2015
 */

public class Model implements ControllerListener {
	private long score;
	private Grid grid;
	private GridBackup backup;
	private Boolean normalMode;
	private int tileIndex;
	
	public Model () {
		this.score = 0;
		this.grid = new Grid();
		this.normalMode = true;
	}

	@Override
	public void buttonClicked(ButtonClickEvent e) {
		if (this.normalMode) {
			switch (e.getButton()) {
			case NEW_GAME:
				this.score = 0;
				this.grid = new Grid();
				break;
			case BONUS_ERASE:
				Erase erase = new Erase(grid);
				if (erase.isAvailable(score) ) {
					normalMode = false;
					while (!normalMode) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							
						}
					}
					score-=erase.apply(this.tileIndex);
				}
				break;
			case BONUS_PAUSE:
				Pause pause = new Pause(grid.getQueue());
				if (pause.isAvailable(this.score)) {
					score-=pause.apply();
				}
				break;
			case BONUS_RANDOM:
				RandomModifier rndMod = new RandomModifier(grid);
				score = rndMod.apply(score);
				break;
			case BONUS_SEE:
				
				break;
			case BONUS_SWAP:
				Swap swap = new Swap(grid);
				int tileIndex1, tileIndex2;
				
				if (swap.isAvailable(score) ) {
					normalMode = false;
					while (!normalMode) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							
						}
					}
					tileIndex1 = this.tileIndex;
					normalMode = false;
					while (!normalMode) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							
						}
					}
					tileIndex2 = this.tileIndex;
					score-=swap.apply(tileIndex1, tileIndex2);
				}
				break;
			case BONUS_TURNLEFT:
				TurnLeft turnLeft = new TurnLeft(grid);
				
				if (turnLeft.isAvailable(score) ) {
					normalMode = false;
					while (!normalMode) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							
						}
					}
					score-=turnLeft.apply(this.tileIndex);
				}
				break;
			case BONUS_TURNRIGHT:
				TurnRight turnRight = new TurnRight(grid);
				
				if (turnRight.isAvailable(score) ) {
					normalMode = false;
					while (!normalMode) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							
						}
					}
					score-=turnRight.apply(this.tileIndex);
				}
				break;
			case BONUS_UNDO:
				Undo undo = new Undo();
				if (undo.isAvailable(score)) {
					score-=undo.apply(grid, backup);
				}
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void gridMoved(MovementEvent e) {
		if (this.normalMode) {
			grid.move(e.getDirection(), backup, score);
		}
	}

	@Override
	public void tileClicked(TileClickEvent e) {
		if (!this.normalMode) {
			this.tileIndex = e.getTileIndex();
			normalMode = true;
		}
	}
}
