import java.util.ArrayList;
import java.util.List;

public class Round {
    private int state;
    private int dealer;
    private int smallBLind;
    private int bigBlind;
    private Deck deck;
    private List<Seat> players;

    public Round(List<Player> players){
        this.players = new ArrayList<Seat>();
        for(Player player : players){
            this.players.add(new Seat(player));
        }

        dealer = 0;
        smallBLind = 1;
        bigBlind = smallBLind + 1 % players.size();
        deck = new Deck();
    }

    public void startRound() throws Exception {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < players.size(); i++) {
                Card card = deck.giveCard();
                players.get(i).giveCard(card);
            }
        }
    }

    public void printPlayersHand() throws Exception {
            for (int i = 0; i < players.size(); i++) {
                Seat player = players.get(i);
                List<Card> cards = player.showHand();
                System.out.printf("Jogador %s%n", player.getPlayerName());
                for (Card card : cards) {
                    System.out.println(card.print());
                }
            }
        }
}
