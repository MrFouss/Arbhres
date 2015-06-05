package arbhres.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.EventListenerList;

import arbhres.controller.events.MovementEvent;
import arbhres.controller.events.MovementEvent.Direction;
import arbhres.controller.events.TileClickEvent;
import arbhres.controller.events.ButtonClickEvent;
import arbhres.controller.events.ButtonClickEvent.Button;
import arbhres.controller.listeners.ControllerListener;

/**
 * @author	Maxime Brodat <maxime.brodat@fouss.fr>
 * @version	1.0
 * @since	05/21/2015
 */
public class Controller extends InputAdapter {
	private Boolean normalMode = true;
	private final EventListenerList listeners = new EventListenerList();

	/* NEEDED OVERRIDEN METHODS */

	@Override
	public void keyTyped(KeyEvent e) {
		if (normalMode) {
			fireKeyPressed(e);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Click coordinates: [" + e.getX() + ";" + e.getY()
				+ "]");
		if (normalMode) {
			// TODO fireButtonClicked(getButton(e.getX(), e.getY()));
		} else {
			// TODO fireTileClicked(getButton(e.getX(), e.getY()));
		}
	}

	/* LISTENER METHODS */

	/**
	 * Add a new controller listener to the list of listeners
	 * 
	 * @param listener Listener to add to the list
	 */
	public void addControllerListener(ControllerListener listener) {
		listeners.add(ControllerListener.class, listener);
	}

	/**
	 * Remove a controller listener of the list of listeners
	 * 
	 * @param listener Listener to remove of the list
	 */
	public void removeControllerListener(ControllerListener listener) {
		listeners.remove(ControllerListener.class, listener);
	}

	/**
	 * Get the list of listeners actually listening to the controller
	 * 
	 * @return List of listeners
	 */
	public ControllerListener[] getControllerListeners() {
		return listeners.getListeners(ControllerListener.class);
	}
	
	/* LISTENERS FIRE METHODS */

	/**
	 * Notifies the listeners a key has been pressed
	 * 
	 * @param keyTyped Event linked to the typed key
	 */
	protected void fireKeyPressed(KeyEvent keyTyped) {
		MovementEvent event = null;
		for (ControllerListener listener : getControllerListeners()) {
			if (event == null) {
				switch (keyTyped.getKeyCode()) {
				case KeyEvent.VK_UP:
					event = new MovementEvent(Direction.UP);
					break;
				case KeyEvent.VK_LEFT:
					event = new MovementEvent(Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					event = new MovementEvent(Direction.RIGHT);
					break;
				case KeyEvent.VK_DOWN:
					event = new MovementEvent(Direction.DOWN);
					break;
				}
			}
			listener.gridMoved(event);
		}
	}

	/**
	 * Notifies the listeners a button has been clicked
	 * 
	 * @param action Event containing which button has been clicked
	 */
	protected void fireButtonClicked(Button button) {
		ButtonClickEvent event = null;
		for (ControllerListener listener : getControllerListeners()) {
			if (event == null) {
				event = new ButtonClickEvent(button);
			}
			listener.buttonClicked(event);
		}
	}

	/**
	 * Notifies the listeners a tile has been clicked
	 * 
	 * @param action Event containing which tile has been clicked
	 */
	protected void fireTileClicked(int tileIndex) {
		TileClickEvent event = null;
		for (ControllerListener listener : getControllerListeners()) {
			if (event == null) {
				event = new TileClickEvent(tileIndex);
			}
			listener.tileClicked(event);
		}
	}
}
