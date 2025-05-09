import java.util.ArrayList;
import java.util.List;

public class Round {
    private int state;
    private int dealer;
    private int smallBLind;
    private int bigBlind;
    private int smallBlindValue;
    private int bigBlindValue;
    private int maxBet;
    private Deck deck;
    private List<Seat> players;
    private boolean hasRaised;

    public Round(List<Player> players){
        this.players = new ArrayList<Seat>();
        for(Player player : players){
            this.players.add(new Seat(player));
        }
        dealer = 0;
        smallBLind = 1;
        bigBlind = smallBLind + 1 % players.size();
        smallBlindValue = 5;
        bigBlindValue = smallBlindValue * 2;
        maxBet = bigBlind;
        deck = new Deck();
    }

    public void startRound() throws Exception {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < players.size(); i++) {
                Card card = deck.giveCard();
                players.get(i).giveCard(card);

            }
        }
        for (int i = 0; i < players.size(); i++) {
            Seat seat = players.get(i);
            seat.setActive(true);
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

    public void preFlop() throws Exception {
        hasRaised = false;

        // Fazer o small blind e o big blind apostarem
        Seat sbPlayer = players.get(smallBLind);
        Seat bbPlayer = players.get(bigBlind);
        sbPlayer.makeBet(smallBlindValue);
        bbPlayer.makeBet(bigBlindValue);

        for (int i = 0; i < players.size(); i++) {
            int playerPosition = (i + bigBlind + 1) % players.size();
            Seat playerToDecide = players.get(playerPosition);
            if(!playerToDecide.isActive()){
                continue;
            }
            List<Actions> actions = new ArrayList<Actions>();
            if(playerPosition == smallBLind){
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
            }else if(playerPosition == bigBlind){
                actions.add(Actions.RAISE);
                actions.add(Actions.CHECK);
            }else{
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
            }
            Actions action = playerToDecide.getAction(actions);
            processPlayerAction(i, action);
        }

        while(hasRaised){
            hasRaised = false;
            for (int i = 0; i < players.size(); i++) {
                Seat player = players.get(i);
                if(!player.isActive()){
                    continue;
                }
                List<Actions> actions = new ArrayList<Actions>();
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
                Actions action = player.getAction(actions);
                processPlayerAction(i, action);
            }
        }

        for (int i = 0; i < players.size(); i++) {
            Seat player = players.get(i);

            if(!player.isActive()){
                System.out.printf("Jogador %s deu fold%n ", player.getPlayerName());
            }else{
                System.out.printf("Jogador %s apostou %s%n ", player.getPlayerName(), player.getBet());
            }

        }
    }

    private void processPlayerAction(int seat, Actions action) throws Exception {
        if(action == Actions.FOLD){
            players.get(seat).setActive(false);
        }else if(action == Actions.CHECK){

        }else if(action == Actions.RAISE){
            hasRaised = true;
            // pegar o valor que foi aumentado, atualizar a max bet
            Seat player = players.get(seat);
            int value = player.bet();
            maxBet += value;
            player.makeBet(value);
        }else if(action == Actions.CALL){
            Seat player = players.get(seat);
            int valueToBet = maxBet - player.getBet();
            player.makeBet(valueToBet);
        }else if(action == Actions.BET){
            hasRaised = true;
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
