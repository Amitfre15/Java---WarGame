public class Player {
   private final String name; //player's name
   Deck game; //playing deck
   Deck win; //winning deck

    /** Player object builder. */
    public Player(String name){
        this.name=name;
        this.game=new Deck(false);
        this.win=new Deck(false);
    }

    /**
     * Adds a card to the playing deck.
     *
     * @param gameCard The card to add
     */
    public void addCardGame(Card gameCard){
        this.game.addCard(gameCard);
    }

    /**
     * Adds a card to the winning deck.
     *
     * @param winCard The card to add
     */
    public void addCardWin(Card winCard){
        this.game.addCard(winCard);
    }

    /** Draws a card to the game and removes it from the deck. */
    public void drawCard(){
        this.game.removeTopCard();
    }

    /**
     * Checks if this player is out of cards.
     *
     * @return Both player's decks are empty: true, else: false
     */
    public boolean outOfCards(){
        return (this.game.isEmpty() && this.win.isEmpty());
    }

    /** Returns the player's name. */
    public String toString(){
        return this.name;
    }

    public void switchDeck(){
        this.win.shuffle();
        this.game = this.win;
        this.win = new Deck(false);
    }

    /** Returns the player's playing deck. */
    public Deck getGame(){
        return this.game;
    }
    /** Receives and sets the player's playing deck. */
    public void setGame(Deck game){
        this.game=game;
    }

    /** Returns the player's winning deck. */
    public Deck getWin(){
        return this.win;
    }
    /** Receives and sets the player's winning deck. */
    public void setWin(Deck win){
        this.win=win;
    }
}
