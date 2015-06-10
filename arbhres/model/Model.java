package arbhres.model;

import javax.swing.event.EventListenerList;

import arbhres.controller.listeners.ControllerListener;
import arbhres.events.ButtonClickEvent;
import arbhres.events.MovementEvent;
import arbhres.events.TileClickEvent;
import arbhres.model.listeners.ModelListener;
import arbhres.model.modifiers.*;
import arbhres.model.structure.Grid;
import arbhres.model.structure.GridBackup;

/**
 * @author	Pierre Brunet "pierre.brunet@krophil.fr"
 * @version	1.0
 * @since	05/21/2015
 */

public class Model implements ControllerListener {
	private long score;
	private Grid grid;
	private GridBackup backup;
	private Boolean pressButton;
	private Boolean moveGrid;
	private Boolean clickTile;
	private int tileIndex;
	private int blindTurn;
	private int seeTurn;
	private final EventListenerList listeners = new EventListenerList();
	
	public Model () {
		this.score = 0;
		this.grid = new Grid();
		this.pressButton = true;
		this.moveGrid = true;
		this.clickTile = false;
		this.blindTurn = 0;
		this.seeTurn = 0;
	}
	
	/* LISTENER METHODS */

	/**
	 * Add a new model listener to the list of listeners
	 * 
	 * @param listener the listener to add to the list
	 */
	public void addModelListener(ModelListener listener) {
		listeners.add(ModelListener.class, listener);
	}

	/**
	 * Remove a model listener of the list of listeners
	 * 
	 * @param listener the listener to remove of the list
	 */
	public void removeModelListener(ModelListener listener) {
		listeners.remove(ModelListener.class, listener);
	}

	/**
	 * Get the list of listeners actually listening to the controller
	 * 
	 * @return the list of listeners
	 */
	public ModelListener[] getModelListeners() {
		return listeners.getListeners(ModelListener.class);
	}

	/* OVERRIDEN METHODS */
	
	@Override
	public void buttonClicked(ButtonClickEvent e) {
		if (this.pressButton) {
			this.moveGrid = false;
			this.pressButton = false;
			switch (e.getButton()) {
			case NEW_GAME:
				this.score = 0;
				this.grid = new Grid();
				this.pressButton = true;
				this.moveGrid = true;
				this.clickTile = false;
				this.blindTurn = 0;
				this.seeTurn = 0;
				break;
			case BONUS_ERASE:
				Erase erase = new Erase(grid);
				if (erase.isAvailable(score) ) {
					this.clickTile = true;
					while (this.clickTile) {
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
				this.blindTurn+=rndMod.getBlindTurns();
				this.seeTurn+=rndMod.getSeeTurns();
				break;
			case BONUS_SEE:
				See see = new See();
				if(see.isAvailable(this.score)) {
					score -= see.apply();
					this.seeTurn += see.getSeeTurns();
				}
				break;
			case BONUS_SWAP:
				Swap swap = new Swap(grid);
				int tileIndex1, tileIndex2;
				
				if (swap.isAvailable(score) ) {
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
					score-=swap.apply(tileIndex1, tileIndex2);
				}
				break;
			case BONUS_TURNLEFT:
				TurnLeft turnLeft = new TurnLeft(grid);
				
				if (turnLeft.isAvailable(score) ) {
					this.clickTile = true;
					while (this.clickTile) {
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
					this.clickTile = true;
					while (this.clickTile) {
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
			this.moveGrid = true;
			this.pressButton = true;
		}
	}
	
	@Override
	public void gridMoved(MovementEvent e) {
		if (this.moveGrid) {
			long scoreChange;
			this.moveGrid = false;
			this.pressButton = false;
			scoreChange = grid.move(e.getDirection(), backup, score);
			if (scoreChange != -1) {
				score+=scoreChange;
				blindTurn = Math.max(blindTurn - 1, -1);
				seeTurn = Math.max(seeTurn - 1, -1);
			}
			this.moveGrid = true;
			this.pressButton = true;
		}
	}

	@Override
	public void tileClicked(TileClickEvent e) {
		if (this.clickTile) {
			this.tileIndex = e.getTileIndex();
			this.clickTile = false;
		}
	}
}
