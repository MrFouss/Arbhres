package arbhres.model;

import javax.swing.event.EventListenerList;

import arbhres.controller.listeners.ControllerListener;
import arbhres.events.ButtonClickEvent;
import arbhres.events.ButtonClickEvent.Button;
import arbhres.events.MovementEvent;
import arbhres.events.MovementTileEvent;
import arbhres.events.StateEvent;
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
	private int targetIndex;
	
	public Model () {
		this.score = 0;
		this.grid = new Grid(this);
		this.pressButton = true;
		this.moveGrid = true;
		this.clickTile = false;
		this.blindTurn = 0;
		this.seeTurn = 0;
		this.targetIndex = -1;
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
		System.out.println("Button clicked: " +e.getButton());
		
		if (this.pressButton) {
			this.moveGrid = false;
			this.pressButton = false;
			switch (e.getButton()) {
			case NEW_GAME:
				this.score = 0;
				this.grid = new Grid(this);
				this.pressButton = true;
				this.moveGrid = true;
				this.clickTile = false;
				this.blindTurn = 0;
				this.seeTurn = 0;
				this.targetIndex = -1;
				grid.initTiles();
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
				Pause pause = new Pause(this.grid.getQueue());
				if (pause.isAvailable(this.score)) {
					score-=pause.apply();
				}
				break;
			case BONUS_RANDOM:
				RandomModifier rndMod = new RandomModifier(this.grid, this.targetIndex);
				score = rndMod.apply(score);
				this.blindTurn+=rndMod.getBlindTurns();
				this.seeTurn+=rndMod.getSeeTurns();
				this.targetIndex=rndMod.getTargetIndex();
				//TODO add fire target
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
		System.out.println("Arrow used : " + e.getDirection());
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
			
			if (this.targetIndex != -1) {
				this.grid.removeTile(this.targetIndex);
				this.targetIndex = -1;
				//TODO fire target ?
			}
			this.fireRefreshGUI();
			this.moveGrid = true;
			this.pressButton = true;
		}
	}

	@Override
	public void tileClicked(TileClickEvent e) {
		System.out.println("Tile clicked (index: " +e.getTileIndex()+ " ; value: " +e.getTileValue() + ")");
		if (this.clickTile) {
			this.tileIndex = e.getTileIndex();
			this.clickTile = false;
		}
	}
	
	/* LISTENERS FIRE METHODS */
	
	/**
	 * Notifies the listeners about the start or the end of blind mode
	 * 
	 * @param state the new state of the blind mode
	 */
	public void fireSwitchBlindMode(boolean state) {
		StateEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new StateEvent(state);
			}
			listener.switchBlindMode(event);
		}
	}
	
	/**
	 * Notifies the listeners about the start or the end of see mode
	 * 
	 * @param state the new state of the see mode
	 */
	protected void fireSwitchSeeMode(boolean state) {
		StateEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new StateEvent(state);
			}
			listener.switchSeeMode(event);
		}
	}
	

	/**
	 * Notifies the listeners that a new tile has been added
	 * 
	 * @param tileIndex the index of the added tile
	 * @param tileValue the value of the added tile
	 */
	public void fireAddTile(int tileIndex,int tileValue) {
		TileClickEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new TileClickEvent(tileIndex, tileValue);
			}
			listener.addTile(event);
		}
	}
	
	/**
	 * Notifies the listeners that a new tile has been removed
	 * 
	 * @param tileIndex the index of the removed tile
	 */
	public void fireRemoveTile(int tileIndex) {
		TileClickEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new TileClickEvent(tileIndex);
			}
			listener.addTile(event);
		}
	}
	
	/**
	 * Notifies the listeners that a move has been moved
	 * @param indexStart  the index of the previous place of the tile
	 * @param indexFinish the index of the new place of the tile
	 */
	public void fireMoveTile(int indexStart, int indexFinish) {
		MovementTileEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new MovementTileEvent(indexStart, indexFinish);
			}
			listener.moveTile(event);
		}
	}
	
	
	
	/**
	 * Notifies the listeners about when a button is clicked
	 * 
	 * @param button the event containing which button has been clicked
	 */
	public void firePressButton(Button button) {
		ButtonClickEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new ButtonClickEvent(button);
			}
			listener.pressButton(event);
		}
	}
	
	/**
	 * Notifies the listeners about when a button is released
	 * 
	 * @param button the event containing which button has been released
	 */
	public void fireReleaseButton(Button button) {
		ButtonClickEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new ButtonClickEvent(button);
			}
			listener.releaseButton(event);
		}
	}
	
	/**
	 * Notifies the listeners about when a tile has to be highlighted
	 * 
	 * @param index The index of the tile
	 */
	public void fireHighlightTile(int index) {		
		TileClickEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new TileClickEvent(index);
			}
			listener.highlightTile(event);
		}
	}
	
	/**
	 * Notifies the listeners about when a tile has to be unhighlighted
	 * 
	 * @param index The index of the tile
	 */
	public void fireUnhighlightTile(int index) {		
		TileClickEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new TileClickEvent(index);
			}
			listener.unhighlightTile(event);
		}
	}
	
	/**
	 * Notifies the listeners about when a tile has to be targeted
	 * 
	 * @param index The index of the tile
	 */
	public void fireAddTarget(int index) {
		TileClickEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new TileClickEvent(index);
			}
			listener.addTarget(event);
		}
	}
	
	/**
	 * Notifies the listeners about when a tile has no longer to be targeted
	 * 
	 * @param index The index of the tile
	 */
	public void fireRemoveTarget(int index) {
		TileClickEvent event = null;
		for (ModelListener listener : getModelListeners()) {
			if (event == null) {
				event = new TileClickEvent(index);
			}
			listener.removeTarget(event);
		}
	}
	
	/**
	 * Notifies the listeners the frame has to be refreshed
	 */
	public void fireRefreshGUI() {
		for (ModelListener listener : getModelListeners()) {
			listener.refreshGUI();
		}
	}
	
	/**
	 * Notifies the listeners the frame has to be restarted
	 */
	public void fireRestartGUI() {
		for (ModelListener listener : getModelListeners()) {
			listener.restartGUI();
		}
	}
	
}
