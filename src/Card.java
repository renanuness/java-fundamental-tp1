public class Card {
    private int value;
    private Suits suit;
    private String rank;

    public Card(Suits suit, int value) {
        if(value == 11){
            this.rank = "J";
        } else if(value == 12){
            this.rank = "Q";
        } else if (value == 13){
            this.rank = "K";
        }else if (value == 1){
            this.rank = "A";
        }else {
            this.rank = String.valueOf(value);
        }
        this.suit = suit;
        this.value = value;
    }

    public String print(){
        return rank + " " + suit.toString();
    }
}
