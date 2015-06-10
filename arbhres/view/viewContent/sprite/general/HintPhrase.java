package arbhres.view.viewContent.sprite.general;

public enum HintPhrase {
    ERASE ("Erase !"),
    PAUSE ("Pause !"),
    SWAP ("Swap !"),
    SEE	("See !"),
    UNDO ("Undo !"),
    TURN_LEFT ("Turn left !"),
    TURN_RIGHT ("Turn right !");
    
    private String phrase;
    
    private HintPhrase(String s) {
    	phrase = s;
    }
    
    public String getPhrase() {
    	return phrase;
    }
}
