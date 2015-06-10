package arbhres;

import java.awt.Graphics;
import java.io.IOException;

import com.sun.xml.internal.ws.api.pipe.NextAction;

import arbhres.view.View;
import arbhres.view.viewContent.sprite.tile.TileLocation;
import arbhres.view.viewContent.sprite.tile.TileType;
import arbhres.controller.Controller;
import arbhres.model.Model;

/**
 * @author	Maxime Brodat <maxime.brodat@fouss.fr>
 * @version	1.0
 * @since	05/21/2015
 */

public class Launcher {
	public static void main(String[] args) throws IOException, InterruptedException {
		Controller controller = new Controller();
		Model model = new Model();
		View view = new View();
	}
}
