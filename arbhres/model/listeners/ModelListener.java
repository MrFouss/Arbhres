package arbhres.model.listeners;

import java.util.EventListener;

import arbhres.events.ButtonClickEvent;
import arbhres.events.TileClickEvent;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	06/10/2015
 * 
 * This class defines all the methods that will be used to notice the Model an event occured
 */
public interface ModelListener extends EventListener {
	
	/**
	 * Notice that a button has been clicked
	 * 
	 * @param e the event containing the information about which button has been clicked
	 */
	public void clickButton(ButtonClickEvent e);
	
	/**
	 * Notice that a tile has to be highlighted
	 * 
	 * @param e the event containing which tile has to be highlighted
	 */
	public void highlightTile(TileClickEvent e);
}