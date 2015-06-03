package arbhres.controller.adapters;

import arbhres.controller.events.MovementEvent;
import arbhres.controller.listeners.MovementListener;

/**
 * @author	Maxime Brodat <maxime.brodat@fouss.fr>
 * @version	1.0
 * @since	05/30/2015
 */

public abstract class MovementAdapter implements MovementListener {
	
    /**
     * Notice the Model the tiles of the grid have to be moved following a direction
     * 
     * @param e Event containing the direction of the movement
     */
    public void gridMoved(MovementEvent e) {
    }
}
