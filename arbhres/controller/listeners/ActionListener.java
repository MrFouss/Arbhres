package arbhres.controller.listeners;

import java.util.EventListener;

import arbhres.controller.events.ActionEvent;

/**
 * @author Maxime Brodat <maxime.brodat@fouss.fr>
 * @version 1.0
 * @since 05/30/2015
 * 
 *        This class defines all the methods that will be used to notice the
 *        Model an event happened
 */

public interface ActionListener extends EventListener {
    /**
     * Notice that the tiles of the grid have to be moved following a direction
     * 
     * @param e
     *            Event containing the direction of the movement
     */
    public void gridMoved(ActionEvent e);

    /**
     * Notice that a button has been clicked
     * 
     * @param e
     *            Event containing which button has been clicked
     */
    public void buttonClicked(ActionEvent e);
}
