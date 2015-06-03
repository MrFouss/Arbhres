package arbhres.controller.listeners;

import java.util.EventListener;

import arbhres.controller.events.TileClickEvent;

/**
 * @author	Maxime Brodat <maxime.brodat@fouss.fr>
 * @version	1.0
 * @since	05/30/2015
 * 
 * This class defines all the methods that will be used to notice the Model a tile has been clicked
 */

public interface TileClickListener extends EventListener {
	
	/**
	 * Notice that a tile has been clicked
	 * 
	 * @param e Event containing the index of the clicked tile
	 */
	public void tileClicked(TileClickEvent e);
}
