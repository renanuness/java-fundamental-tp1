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
        maxBet = bigBlindValue;
        deck = new Deck();
    }

    public void startRound() throws Exception {
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < players.size(); i++) {
                Card card = deck.giveCard();
                players.get(i).giveCard(card);

            }
        }

        for (Seat seat : players) {
            seat.setActive(true);
            seat.setHasToPlay(true);
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
        // Fazer o small blind e o big blind apostarem
        Seat sbPlayer = players.get(smallBLind);
        Seat bbPlayer = players.get(bigBlind);
        sbPlayer.makeBet(smallBlindValue);
        bbPlayer.makeBet(bigBlindValue);

        int playerPosition2 = (bigBlind + 1) % players.size();
        Seat currentPlayer = players.get(playerPosition2);
        while(currentPlayer.hasToPlay()){
            if(!currentPlayer.isActive()){
                continue;
            }
            List<Actions> actions = new ArrayList<Actions>();
            if(playerPosition2 == smallBLind){
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
            }else if(playerPosition2 == bigBlind){
                actions.add(Actions.RAISE);
                actions.add(Actions.CHECK);
            }else{
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
            }
            Actions action = currentPlayer.getAction(actions);
            processPlayerAction(playerPosition2, action);
            playerPosition2 = (playerPosition2 + 1) % players.size();
            currentPlayer = players.get(playerPosition2);
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
            // pegar o valor que foi aumentado, atualizar a max bet
            Seat player = players.get(seat);
            int value = player.bet();
            maxBet += value;
            player.makeBet(value);
            for (int i = 0; i < players.size(); i++) {
                Seat playerToDecide = players.get(i);
                if(!playerToDecide.isActive() || playerToDecide.getPlayerName().equals(player.getPlayerName())){
                    continue;
                }
                playerToDecide.setHasToPlay(true);
            }

        }else if(action == Actions.CALL){
            Seat player = players.get(seat);
            int valueToBet = maxBet - player.getBet();
            player.makeBet(valueToBet);
        }else if(action == Actions.BET){
            Seat player = players.get(seat);
            int value = player.bet();
            player.makeBet(value);
        }

        Seat playerToDecide = players.get(seat);
        playerToDecide.setHasToPlay(false);
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

