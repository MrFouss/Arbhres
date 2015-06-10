package arbhres;

import java.io.IOException;

import arbhres.view.View;
import arbhres.controller.Controller;
import arbhres.model.Model;

/**
 * @author	Maxime Brodat "maxime.brodat@fouss.fr"
 * @version	1.0
 * @since	05/21/2015
 */

public class Launcher {
	public static void main(String[] args) throws IOException, InterruptedException {
		Model model = new Model();
		View view = new View();
		Controller controller = new Controller(view);
		view.addKeyListener(controller);
		view.addMouseListener(controller);
		controller.addControllerListener(model);
		model.addModelListener(view);
		
	}
}
