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

    public void bet(){
        for (int i = 0; i < players.size(); i++) {
            Seat player = players.get(i);
            Actions action = player.getAction();
        }
    }

    public void preFlop(){
        for (int i = 0; i < players.size(); i++) {
            int playerPosition = i + bigBlind + 1 % players.size();
            Seat playerToDecide = players.get(playerPosition);

            if(playerPosition == smallBLind){
                // actions available:
                // call
                // fold
                // raise
                List<Actions> actions = new ArrayList<Actions>();
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
                playerToDecide.getAction(actions);
            }else if(playerPosition == bigBlind){
                // actions available:
                // check
                // raise
                List<Actions> actions = new ArrayList<Actions>();
                actions.add(Actions.RAISE);
                actions.add(Actions.CHECK);
                playerToDecide.getAction(actions);
            }else{
                // actions available:
                // call
                // fold
                // raise
                List<Actions> actions = new ArrayList<Actions>();
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
                playerToDecide.getAction(actions);
            }

            // se alguém deu raise, é preciso passar por todos os jogadores que ainda estão na rodada
            // e perguntar call, raise, fold
            // enquanto alguém der raise, repetir
        }
    }
}
/*
States:
PreFlop
    Call
    Raise
    Fold
    Check (só quem é big blind)
PosFlop

 */
