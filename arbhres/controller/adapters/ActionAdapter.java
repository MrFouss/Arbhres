package arbhres.controller.adapters;

import arbhres.controller.events.ActionEvent;
import arbhres.controller.listeners.ActionListener;

/**
 * @author Maxime Brodat <maxime.brodat@fouss.fr>
 * @version 1.0
 * @since 05/30/2015
 */

public abstract class ActionAdapter implements ActionListener {
    /**
     * Notice the Model the tiles of the grid have to be moved following a
     * direction
     * 
     * @param e
     *            Event containing the direction of the movement
     */
    public void gridMoved(ActionEvent e) {
    }

    /**
     * Notice the Model a button has been clicked
     * 
     * @param e
     *            Event containing which button has been clicked
     */
    public void buttonClicked(ActionEvent e) {
    }
}
