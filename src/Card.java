public class Card {
    final int number;
    final Shape shape;

    public Card(int number, Shape shape){
        this.number = number;
        this.shape = shape;
    }

    int compare(Card other){
        if (this.number < other.number)
            return -1;
        if (this.number > other.number)
            return 1;
        return 0;
    }

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

    public int getNumber() {
        return this.number;
    }

    public Shape getShape() {
        return this.shape;
    }
}
