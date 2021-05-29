import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    List<Card> cards = new ArrayList<>();

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
                            c = new Card(num, Shape.HEARTS);
                    }
                    this.cards.add(c);
                }
            }
        }
    }

    public void addCard(Card c){
        this.cards.add(c);
    }

    public Card removeTopCard(){
        return this.cards.remove(this.cards.size()-1);
    }

    public boolean isEmpty(){
        return this.cards.size() == 0;
    }

    public void shuffle(){
        //deal with size = 0
        int ind1;
        int ind2;
        int size = this.cards.size();
        Card temp;

        for (int i = 0; i < 50; i++){
            ind1 = Main.rnd.nextInt(size);
            ind2 = Main.rnd.nextInt(size);
            temp = this.cards.get(ind1);
            this.cards.set(ind1, this.cards.get(ind2));
            this.cards.set(ind2, temp);
        }
    }
    public Card getCard(){
        return this.cards.get(this.cards.size()-1);
    }
}
