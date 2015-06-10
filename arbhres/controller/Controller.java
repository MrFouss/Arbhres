package arbhres.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.event.EventListenerList;

import arbhres.controller.listeners.ControllerListener;
import arbhres.events.ButtonClickEvent;
import arbhres.events.MovementEvent;
import arbhres.events.TileClickEvent;
import arbhres.events.ButtonClickEvent.Button;
import arbhres.events.MovementEvent.Direction;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	05/21/2015
 */
public class Controller extends InputAdapter {
	private final EventListenerList listeners = new EventListenerList();

	/* NEEDED OVERRIDEN METHODS */

	@Override
	public void keyTyped(KeyEvent e) {
		fireKeyPressed(e);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (isButton(e.getX(), e.getY())) {
			fireButtonClicked(getButton(e.getX(), e.getY()));
		} else if (isTile(e.getX(), e.getY())) {
			fireTileClicked(getTileIndex(e.getX(), e.getY()));
		}
	}

	/* LISTENER METHODS */

	/**
	 * Add a new controller listener to the list of listeners
	 * 
	 * @param listener the listener to add to the list
	 */
	public void addControllerListener(ControllerListener listener) {
		listeners.add(ControllerListener.class, listener);
	}

	/**
	 * Remove a controller listener of the list of listeners
	 * 
	 * @param listener the listener to remove of the list
	 */
	public void removeControllerListener(ControllerListener listener) {
		listeners.remove(ControllerListener.class, listener);
	}

	/**
	 * Get the list of listeners actually listening to the controller
	 * 
	 * @return the list of listeners
	 */
	public ControllerListener[] getControllerListeners() {
		return listeners.getListeners(ControllerListener.class);
	}
	
	/* LISTENERS FIRE METHODS */

	/**
	 * Notifies the listeners a key has been pressed
	 * 
	 * @param keyTyped the event linked to the typed key
	 */
	protected void fireKeyPressed(KeyEvent keyTyped) {
		MovementEvent event = null;
		for (ControllerListener listener : getControllerListeners()) {
			if (event == null) {
				switch (keyTyped.getKeyCode()) {
				case KeyEvent.VK_UP:
					event = new MovementEvent(Direction.UP);
					listener.gridMoved(event);
					break;
				case KeyEvent.VK_LEFT:
					event = new MovementEvent(Direction.LEFT);
					listener.gridMoved(event);
					break;
				case KeyEvent.VK_RIGHT:
					event = new MovementEvent(Direction.RIGHT);
					listener.gridMoved(event);
					break;
				case KeyEvent.VK_DOWN:
					event = new MovementEvent(Direction.DOWN);
					listener.gridMoved(event);
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * Notifies the listeners a button has been clicked
	 * 
	 * @param action the event containing which button has been clicked
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
	 * @param action the event containing which tile has been clicked
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
