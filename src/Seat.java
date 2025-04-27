import java.util.ArrayList;
import java.util.List;

public class Seat {
    private Player player;
    private List<Card> cards;
    private int bet;
    private Actions action;

    public Seat(Player player){
        this.player = player;
        this.cards = new ArrayList<Card>();
    }

    public void giveCard(Card card) throws Exception {
        if(cards.size() == 2){
            throw new Exception();
        }
        cards.add(card);
    }

    public List<Card> showHand(){
        return cards;
    }

    public String getPlayerName(){
        return player.getName();
    }
}
