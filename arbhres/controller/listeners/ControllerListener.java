package arbhres.controller.listeners;

import java.util.EventListener;

import arbhres.events.ButtonClickEvent;
import arbhres.events.MovementEvent;
import arbhres.events.TileClickEvent;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	06/05/2015
 * 
 * This class defines all the methods that will be used to notice the Model an event occured
 */
public interface ControllerListener extends EventListener {
	
	/**
	 * Notice that a button has been clicked
	 * 
	 * @param e the event containing the information about which button has been clicked
	 */
	public void buttonClicked(ButtonClickEvent e);
	
	/**
	 * Notice that the tiles of the grid have to be moved following a direction
	 * 
	 * @param e the event containing the direction of the movement
	 */
	public void gridMoved(MovementEvent e);
	
	/**
	 * Notice that a tile has been clicked
	 * 
	 * @param e the event containing the index of the clicked tile
	 */
	public void tileClicked(TileClickEvent e);
}
