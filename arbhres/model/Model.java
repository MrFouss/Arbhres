package arbhres.model;

import arbhres.controller.Controller;
import arbhres.controller.events.ButtonClickEvent;
import arbhres.controller.events.MovementEvent;
import arbhres.controller.events.TileClickEvent;
import arbhres.controller.listeners.ControllerListener;

/**
 * @author	Pierre Brunet <pierre.brunet@krophil.fr>
 * @version	1.0
 * @since	05/21/2015
 */

public class Model implements ControllerListener {
	private long score;
	private Grid grid;
	private Boolean normalMode;
	
	public Model (Controller controller) {
		this.score = 0;
		this.grid = new Grid();
	}

	@Override
	public void buttonClicked(ButtonClickEvent e) {
		if (normalMode) {
			
		}
	}

	@Override
	public void gridMoved(MovementEvent e) {
		if (normalMode) {
			
		}
	}

	@Override
	public void tileClicked(TileClickEvent e) {
		if (!normalMode) {
			
		}
	}
}
