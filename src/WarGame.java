public class WarGame {
    Player first;
    Player second;
    Deck firstDeck;
    Deck secondDeck;

    public WarGame(String first, String second){
        this.first=new Player(first);
        this.second=new Player(second);
        this.firstDeck = new Deck(false);
        this.secondDeck = new Deck(false);
    }

    public void initializeGame(){
        System.out.println("Initializing the game...");
        Deck wholeDeck=new Deck(true);
        wholeDeck.shuffle();
        if(this.first.getName().compareTo(this.second.getName())>0) {
            Player temp = this.first;
            this.first = this.second;
            this.second = temp;
//            for (int i = 0; i < 26; i++) {
//                this.firstDeck.addCard(wholeDeck.removeTopCard());
//                this.secondDeck.addCard(wholeDeck.removeTopCard());
//            }
        }
//        else  {
            for (int j = 0; j < 26; j++) {
                this.firstDeck.addCard(wholeDeck.removeTopCard());
                this.secondDeck.addCard(wholeDeck.removeTopCard());
            }
//            }
        this.first.setGame(firstDeck);
        this.second.setGame(secondDeck);

    }

    public String start(){
        initializeGame();
        Deck drewFirst=new Deck(false);
        Deck drewSecond=new Deck(false);
        int war = -1;
        int index=1;
        while(!(this.first.outOfCards()) && !(this.second.outOfCards()) ) {
            if (war == -1) {
                System.out.println("------------------------- Round number " + index + " -------------------------");
                index++;
            }
            Card cardFirst=this.first.getGame().removeTopCard();
            Card cardSecond=this.second.getGame().removeTopCard();

            if (war >= 0 && war < 2){
                System.out.println(this.first.getName()+ " drew a war card");
                System.out.println(this.second.getName()+ " drew a war card");

                if (this.first.getGame().isEmpty()) {
                    if (this.first.getWin().isEmpty())
                        break;
                    switchDeck(first);
                }

                if (this.second.getGame().isEmpty()) {
                    if (this.second.getWin().isEmpty())
                        break;
                    switchDeck(second);
                }

                drewFirst.addCard(cardFirst);
                drewSecond.addCard(cardSecond);
                war++;
                continue;
            }

            System.out.println(this.first.getName() + " drew " + cardFirst.toString());
            drewFirst.addCard(cardFirst);
            System.out.println(this.second.getName() + " drew " + cardSecond.toString());
            drewSecond.addCard(cardSecond);
            int comparison = drewFirst.getCard().compare(drewSecond.getCard());

            if (comparison > 0){
                if (war!=2)
                    System.out.println(this.first.getName() + " won");
                else {
                    System.out.println(this.first.getName() + " won the war");
                }
                while(!drewFirst.isEmpty()){
                    this.first.getWin().addCard(drewSecond.removeTopCard());
                    this.first.getWin().addCard(drewFirst.removeTopCard());
                }
//                while(!drewSecond.isEmpty()){
//                    this.first.getWin().addCard(drewSecond.removeTopCard());
//                }

            } else if (comparison < 0){
                if (war!=2)
                    System.out.println(this.second.getName() + " won");
                else {
                    System.out.println(this.second.getName() + " won the war");

                }
                while(!drewSecond.isEmpty()){
                    this.second.getWin().addCard(drewSecond.removeTopCard());
                    this.second.getWin().addCard(drewFirst.removeTopCard());
                }
//                while(!drewSecond.isEmpty()){
//                    this.second.getWin().addCard(drewSecond.removeTopCard());
//                }
            } else { // war
                System.out.println("Starting a war...");

                war = 0;
            }
            if (war == 2)
                war = -1;
            if (this.first.getGame().isEmpty()) {
                if (this.first.getWin().isEmpty())
//                    if(war==2){
//                        continue;
//                    }
                    break;
                switchDeck(first);
            }

            if (this.second.getGame().isEmpty()) {
                if (this.second.getWin().isEmpty())
                    break;
                switchDeck(second);
            }
        }
        if (this.first.outOfCards())
            return this.second.getName();
        return this.first.getName();
    }

    public void switchDeck(Player p){
        p.getWin().shuffle();
        p.setGame(p.getWin());
//        p.getGame().shuffle();
        p.setWin(new Deck(false));
    }
}
