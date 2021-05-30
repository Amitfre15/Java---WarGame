public class WarGame {
    Player first;
    Player second;
    Deck firstDeck; //holds the cards that were dealt to the first player
    Deck secondDeck;
    private static final int HALF_DECK = 26;

    /**
     * WarGame object builder - creates two players and their playing decks.
     *
     * @param first,second - the players' names
     */
    public WarGame(String first, String second){
        this.first=new Player(first);
        this.second=new Player(second);
        this.firstDeck = new Deck(false);
        this.secondDeck = new Deck(false);
    }

    /**
     * Creates a full shuffled deck and deals the cards one at a time, begins
     * with the player whose name is lexicographic first.
     */
    public void initializeGame(){
        System.out.println("Initializing the game...");
        Deck wholeDeck=new Deck(true);
        wholeDeck.shuffle();

        //check whose name is lexicographic first and switch if needed
        if(this.first.toString().compareTo(this.second.toString())>0) {
            Player temp = this.first;
            this.first = this.second;
            this.second = temp;
        }
        //deal the cards
        for (int j = 0; j < HALF_DECK; j++) {
            this.firstDeck.addCard(wholeDeck.removeTopCard());
            this.secondDeck.addCard(wholeDeck.removeTopCard());
        }
        this.first.setGame(firstDeck);
        this.second.setGame(secondDeck);
    }

    /**
     * Starts a war game and manages it.
     * Calls initialize function, deals with regular and war rounds of the
     * game, calls appropriate print functions
     *
     * @return winner's name
     */
    public String start(){
        initializeGame();
        //holds the cards that the players drew
        Deck drewFirst=new Deck(false);
        Deck drewSecond=new Deck(false);
        int war = -1; //indicates whether there's a war
        int roundNum = 1;

        //round loop
        while(!(this.first.outOfCards()) && !(this.second.outOfCards())) {
            if (war == -1) { //regular round
                System.out.println("------------------------- Round number " +
                        roundNum + " -------------------------");
                roundNum++;
            }
            //getting the top card from each player's playing deck
            Card cardFirst=this.first.getGame().removeTopCard();
            Card cardSecond=this.second.getGame().removeTopCard();

            //manage a war
            if (war >= 0 && war < 2){
                drewFirst.addCard(cardFirst);
                drewSecond.addCard(cardSecond);
                if (warManage() == -1) //a player is out of cards
                    break;
                war++;
                continue;
            }

            printDrew(cardFirst, cardSecond);
            drewFirst.addCard(cardFirst);
            drewSecond.addCard(cardSecond);
            int comparison = drewFirst.getCard().compare(drewSecond.getCard());

            //deal with the round result
            if (comparison > 0){ //first won
                printRoundResult(this.first, war);
                //move all drew cards to winner's winning deck
                while(!drewFirst.isEmpty()){
                    this.first.getWin().addCard(drewSecond.removeTopCard());
                    this.first.getWin().addCard(drewFirst.removeTopCard());
                }
            } else if (comparison < 0){ //second won
                printRoundResult(this.second, war);
                while(!drewSecond.isEmpty()){
                    this.second.getWin().addCard(drewSecond.removeTopCard());
                    this.second.getWin().addCard(drewFirst.removeTopCard());
                }
            } else { //war
                System.out.println("Starting a war...");
                war = 0;
            }

            if (war == 2) //end of war
                war = -1;

            //deal with empty playing deck
            if (this.first.getGame().isEmpty()) {
                if (this.first.getWin().isEmpty())
                    break;
                first.switchDeck();
            }

            if (this.second.getGame().isEmpty()) {
                if (this.second.getWin().isEmpty())
                    break;
                second.switchDeck();
            }
        }
        //game ended
        if (this.first.outOfCards())
            return this.second.toString();
        return this.first.toString();
    }

    /**
     * Manages a war situation.
     *
     * @return The war ended when both players have cards: 0, a player lost the
     * game during the war: -1
     */
    public int warManage(){
        System.out.println(this.first.toString()+ " drew a war card");
        System.out.println(this.second.toString()+ " drew a war card");

        //deal with empty playing deck
        if (this.first.getGame().isEmpty()) {
            if (this.first.getWin().isEmpty())
                return -1;
            first.switchDeck();
        }

        if (this.second.getGame().isEmpty()) {
            if (this.second.getWin().isEmpty())
                return -1;
            second.switchDeck();
        }
        return 0;
    }

    /** Prints the cards each player drew. */
    public void printDrew(Card card1, Card card2){
        System.out.println(this.first.toString() +
                " drew " + card1.toString());
        System.out.println(this.second.toString() +
                " drew " + card2.toString());
    }

    /** Prints round winner's name according to round type (regular/war). */
    public void printRoundResult(Player winner, int war){
        if (war!=2)
            System.out.println(winner.toString() + " won");
        else {
            System.out.println(winner.toString() + " won the war");
        }
    }
}
