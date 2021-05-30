public class Card {
    final int number;
    final Shape shape;

    /** Card object builder. */
    public Card(int number, Shape shape){
        this.number = number;
        this.shape = shape;
    }

    /**
     * Compares between this card and a given card by their number value.
     *
     * @param other The card to compare with
     * @return This card is bigger: 1, other card is bigger: -1, equal: 0
     */
    int compare(Card other){
        if (this.number < other.number)
            return -1;
        if (this.number > other.number)
            return 1;
        return 0;
    }

    /**
     * Creates a string representation of this card.
     * The format is "{value/name} of {shape}".
     *
     * @return The representing string
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        if (this.number == 1 || this.number > 10){
            switch (this.number){
                case 1:
                    str.append("Ace");
                    break;
                case 11:
                    str.append("Jack");
                    break;
                case 12:
                    str.append("Queen");
                    break;
                case 13:
                    str.append("King");
                    break;
            }
        }
        else str.append((this.number));
        str.append(" of ");

        switch (this.shape){
            case CLUBS:
                str.append("♠");
                break;
            case DIAMONDS:
                str.append("♦");
                break;
            case SPADES:
                str.append("♣");
                break;
            case HEARTS:
                str.append("♥");
                break;
        }
        return str.toString();
    }

    /** Returns this card's numeric value. */
    public int getNumber() {
        return this.number;
    }

    /** Returns this card's shape/symbol. */
    public Shape getShape() {
        return this.shape;
    }
}
