public class Player {
   String name;
   Deck game;
   Deck win;

    public Player(String name){
        this.name=name;
        this.game=new Deck(false);
        this.win=new Deck(false);

    }

    public void addCardGame(Card gameCard){
        this.game.addCard(gameCard);
    }

    public void addCardWin(Card winCard){
        this.game.addCard(winCard);
    }

    public void drawCard(){
        this.game.removeTopCard();
    }

    public boolean outOfCards(){
        return (this.game.isEmpty() && this.win.isEmpty());
    }

    public String toString(){
        return this.name;

    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public Deck getGame(){
        return this.game;
    }
    public void setGame(Deck game){
        this.game=game;
    }

    public Deck getWin(){
        return this.win;
    }
    public void setWin(Deck win){
        this.win=win;
    }

}
