package arbhres.model.listeners;

import java.util.EventListener;

import arbhres.events.ButtonClickEvent;
import arbhres.events.MovementTileEvent;
import arbhres.events.TileClickEvent;
import arbhres.events.StateEvent;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	06/10/2015
 * 
 * This class defines all the methods that will be used to notice an event in the model occured
 */
public interface ModelListener extends EventListener {
	
	/**
	 * Switch the "blind" mode (on/off)
	 * 
	 * @param e the event containing if the mode has to be on or off
	 */
	public void switchBlindMode(StateEvent e);
	
	/**
	 * Switch the "see" mode (on/off)
	 * 
	 * @param e the event containing if the mode has to be on or off
	 */
	public void switchSeeMode(StateEvent e);
	
	/**
	 * Notice a new tile has to be added
	 * 
	 * @param e the event containing the position and the value of the new tile
	 */
	public void addTile(TileClickEvent e);
	
	/**
	 * Notice a tile has to be removed
	 * 
	 * @param e the event containing the position of the removed tile
	 */
	public void removeTile(TileClickEvent e);
	
	/**
	 * Notice a tile has moved
	 * 
	 * @param e the event containing the position of the moved tile
	 */
	public void moveTile(MovementTileEvent e);
	
	/**
	 * Notice that a button has been pressed
	 * 
	 * @param e the event containing the information about which button has been pressed
	 */
	public void pressButton(ButtonClickEvent e);
	
	/**
	 * Notice that a button has been released
	 * 
	 * @param e the event containing the information about which button has been released
	 */
	public void releaseButton(ButtonClickEvent e);
	
	/**
	 * Notice a tile has to be highlighted
	 * 
	 * @param e the event containing the position of the highlighted tile
	 */
	public void highlightTile(TileClickEvent e);
	
	/**
	 * Notice a tile has to be unhighlighted
	 * 
	 * @param e the event containing the position of the unhighlighted tile
	 */
	public void unhighlightTile(TileClickEvent e);
	
	/**
	 * Notice a tile has to be targeted
	 * 
	 * @param e the event containing the position of the targeted tile
	 */
	public void addTarget(TileClickEvent e);
	
	/**
	 * Notice a tile has to be untargeted
	 * 
	 * @param e the event containing the position of the untargeted tile
	 */
	public void removeTarget(TileClickEvent e);
	
	/**
	 * Notice the frame has to be refreshed
	 */
	public void refreshGUI();
	
	/**
	 * Notice the frame has to be restarted
	 */
	public void restartGUI();
}