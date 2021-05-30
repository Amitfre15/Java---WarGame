import java.util.ArrayList;

public class Deck {
    ArrayList<Card> cards = new ArrayList<>();

    /**
     * Deck object builder.
     *
     * @param initalize boolean variable that indicates whether to create a
     * full deck or to leave it empty
     */
    public Deck(boolean initalize) {
        if (initalize) {
            for (int s = 0; s < 4; s++) {
                for (int num = 1; num < 14; num++) {
                    Card c;
                    switch (s){
                        case 0:
                            c = new Card(num, Shape.CLUBS);
                            break;
                        case 1:
                            c = new Card(num, Shape.DIAMONDS);
                            break;
                        case 2:
                            c = new Card(num, Shape.SPADES);
                            break;
                        case 3:
                            c = new Card(num, Shape.HEARTS);
                            break;
                        default:
                            c = new Card(0, Shape.HEARTS);
                    }
                    this.cards.add(c);
                }
            }
        }
    }

    /**
     * Adds a card to this deck.
     *
     * @param c The card to add
     */
    public void addCard(Card c){
        this.cards.add(c);
    }

    /** Removes the top card from the deck. */
    public Card removeTopCard(){
        return this.cards.remove(this.cards.size()-1);
    }

    /** Returns if this deck is empty (no cards left in it). */
    public boolean isEmpty(){
        return this.cards.size() == 0;
    }

    /** Shuffles this deck (mixes the cards' positions in it). */
    public void shuffle(){
        int ind1; //index of first card to mix
        int ind2; //index of second card to mix
        int size = this.cards.size();
        Card temp;

        for (int i = 0; i < 50; i++){
            ind1 = Main.rnd.nextInt(size); //generates a random card index
            ind2 = Main.rnd.nextInt(size);
            //swap the cards indexes in the list
            temp = this.cards.get(ind1);
            this.cards.set(ind1, this.cards.get(ind2));
            this.cards.set(ind2, temp);
        }
    }

    /** Returns the last card in the deck. */
    public Card getCard(){
        return this.cards.get(this.cards.size()-1);
    }
}
