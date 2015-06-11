package arbhres.view.viewContent.sprite.general;

public enum HintPhrase {
    HINT_ERASE 		("Erase ! Click on a tile to erase it."),
    HINT_PAUSE 		("Pause ! No tile added to the grid for the next 3 turns."),
    HINT_SWAP 		("Swap ! Click on 2 tiles to swap them."),
    HINT_SEE		("See ! You can see the 3 upcomming tiles for 3 turns."),
    HINT_UNDO 		("Undo ! Undo what you just did."),
    HINT_TURN_LEFT 	("Turn left ! Turn on of the 4 corner squares (anticlockwise)."),
    HINT_TURN_RIGHT ("Turn right ! Turn on of the 4 corner squares (clockwise)."),
    HINT_BLIND 		("Blind ! Bad chance, you cannot see anything for 3 turns."),
    HINT_TARGET 	("Target ! The targeted tile will be destroyed at the next turn.");  
    
    private String phrase;
    
    private HintPhrase(String s) {
    	phrase = s;
    }
    
    public String getPhrase() {
    	return phrase;
    }
}
