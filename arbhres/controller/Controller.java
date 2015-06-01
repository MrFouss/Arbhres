package arbhres.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.event.EventListenerList;

import arbhres.controller.events.ActionEvent;
import arbhres.controller.events.ActionEvent.Action;
import arbhres.controller.listeners.ActionListener;

/**
 * @author Maxime Brodat <maxime.brodat@fouss.fr>
 * @version 1.0
 * @since 05/21/2015
 */

public class Controller implements MouseListener, MouseMotionListener,
	KeyListener {
    private Boolean normalMode = true;
    private final EventListenerList listeners = new EventListenerList();

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
	if (normalMode) {
	    fireKeyPressed(e);
	}
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
	// TODO Add the methods linked to the deplacement of the mouse
    }

    @Override
    public void mouseClicked(MouseEvent e) {
	System.out.println("Click coordinates: [" + e.getX() + ";" + e.getY()
		+ "]");
	// TODO Add the methods linked to mouse clicks
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    /* LISTENER METHODS */

    public void addActionListener(ActionListener listener) {
	listeners.add(ActionListener.class, listener);
    }

    public void removeActionListener(ActionListener listener) {
	listeners.remove(ActionListener.class, listener);
    }

    /**
     * Get the list of listeners actually listening to the controller
     * 
     * @return List of listeners
     */
    public ActionListener[] getActionListeners() {
	return listeners.getListeners(ActionListener.class);
    }

    /**
     * Fire method, called if a button is pressed
     * 
     * @param keyTyped
     *            KeyEvent corresponding to the typed key
     */
    protected void fireKeyPressed(KeyEvent keyTyped) {
	ActionEvent event = null;
	for (ActionListener listener : getActionListeners()) {
	    if (event == null) {
		switch (keyTyped.getKeyCode()) {
		case KeyEvent.VK_UP:
		    event = new ActionEvent(Action.MOVE_UP);
		    break;
		case KeyEvent.VK_LEFT:
		    event = new ActionEvent(Action.MOVE_LEFT);
		    break;
		case KeyEvent.VK_RIGHT:
		    event = new ActionEvent(Action.MOVE_RIGHT);
		    break;
		case KeyEvent.VK_DOWN:
		    event = new ActionEvent(Action.MOVE_DOWN);
		    break;
		}
	    }
	    listener.gridMoved(event);
	}
    }
}
