package arbhres.controller.listeners;

import arbhres.controller.events.ButtonClickEvent;
import arbhres.controller.events.MovementEvent;
import arbhres.controller.events.TileClickEvent;

/**
 * @author	Maxime Brodat <maxime.brodat@fouss.fr>
 * @version	1.0
 * @since	06/05/2015
 * 
 * Implements all three methods created in the ControllerListener interface.
 * Like this, the Model that will listen to the Controller won't have to
 * implement all the methods each time a listener is created.
 */
public abstract class ControllerAdapter implements ControllerListener {
	
	public void buttonClicked(ButtonClickEvent e) {}
	
	public void gridMoved(MovementEvent e) {}
	
	public void tileClicked(TileClickEvent e) {}
}
