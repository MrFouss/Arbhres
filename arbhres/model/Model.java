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
		if (this.normalMode) {
			switch (e.getButton()) {
			case NEW_GAME:
				this.score = 0;
				this.grid = new Grid();
				break;
			case BONUS_ERASE:
				
				break;
			case BONUS_PAUSE:
				Pause pause = new Pause(grid.getQueue());
				if (pause.isAvailable(this.score)) {
					pause.apply();
				}
				break;
			case BONUS_RANDOM:
				
				break;
			case BONUS_SEE:
				
				break;
			case BONUS_SWAP:
				
				break;
			case BONUS_TURNLEFT:
				
				break;
			case BONUS_TURNRIGHT:
				
				break;
			case BONUS_UNDO:
				
				break;
			default:
				break;
			}
		}
	}
	
	@Override
	public void gridMoved(MovementEvent e) {
		if (this.normalMode) {
			grid.move(e.getDirection());
		}
	}

	@Override
	public void tileClicked(TileClickEvent e) {
		if (!this.normalMode) {
			
		}
	}
}
