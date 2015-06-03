package arbhres.controller.listeners;

import java.util.EventListener;

import arbhres.controller.events.ButtonClickEvent;

/**
 * @author	Maxime Brodat <maxime.brodat@fouss.fr>
 * @version	1.0
 * @since	05/30/2015
 * 
 * This class defines all the methods that will be used to notice the Model a button has been clicked
 */

public interface ButtonClickListener extends EventListener {
	
	/**
	 * Notice that a button has been clicked
	 * 
	 * @param e Event containing the information about which button has been clicked
	 */
	public void buttonClicked(ButtonClickEvent e);
}
