import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck(){
        cards = new ArrayList<Card>();
        for(Suits suit : Suits.values()){
            for(int j = 1; j <= 13; j++){
                Card card = new Card(suit, j);
                cards.add(card);
            }
        }
        Shuffle();
        Shuffle();
    }


    public Card giveCard(){
        Shuffle();
        return cards.removeFirst();
    }

    public void Shuffle(){
        RandomComparator comparator = new RandomComparator();
        Collections.sort(cards, comparator);
    }
}
