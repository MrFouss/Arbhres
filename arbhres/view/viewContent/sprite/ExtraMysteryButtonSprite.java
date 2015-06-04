package arbhres.view.viewContent.sprite;

import java.awt.Graphics;
import arbhres.view.graphicObject.Picture;
import arbhres.view.viewContent.Sprite;

public enum ExtraMysteryButtonSprite implements Sprite
{
	ERASE		("eraseP"),
	PAUSE		("pauseP"),
	SWAP		("swapP"),
	SEE		("seeP"),
	UNDO		("undoP"),
	TURN_LEFT	("turnLeftP"),
	TURN_RIGHT	("turnRightP"),
	BLIND		("blindP"),
	TARGET		("targetP");

	private Picture sprite;
	
	private ExtraMysteryButtonSprite(String name)
	{
		this.sprite = new Picture(ButtonSprite.MYSTERY.getBox(), path + name + extension);
	}
	
	public void paint(Graphics g)
	{
	    sprite.paint(g);
	}
}
