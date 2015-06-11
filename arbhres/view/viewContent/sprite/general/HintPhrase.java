package arbhres.view.viewContent.sprite.general;

public enum HintPhrase {
    HINT_ERASE 		("Erase !"),
    HINT_PAUSE 		("Pause !"),
    HINT_SWAP 		("Swap !"),
    HINT_SEE		("See !"),
    HINT_UNDO 		("Undo !"),
    HINT_TURN_LEFT 	("Turn left !"),
    HINT_TURN_RIGHT ("Turn right !"),
    HINT_BLIND 		("Blind !"),
    HINT_TARGET 	("Target !");  
    
    private String phrase;
    
    private HintPhrase(String s) {
    	phrase = s;
    }
    
    public String getPhrase() {
    	return phrase;
    }
}
