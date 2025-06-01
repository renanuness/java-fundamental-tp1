package main;

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
    private List<Card> cards;
    private boolean hasRaised;
    private int pot;

    public Round(List<Player> players){
        this.players = new ArrayList<Seat>();
        cards = new ArrayList<>();
        for(Player player : players){
            this.players.add(new Seat(player, 500));
        }
        pot = 0;
        dealer = 0;
        smallBLind = 1;
        bigBlind = smallBLind + 1 % players.size();
        smallBlindValue = 5;
        bigBlindValue = smallBlindValue * 2;
        maxBet = bigBlindValue;
        deck = new Deck();
        hasRaised = false;
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
                System.out.printf("Jogador %s%n", player.getPlayerName());
                player.showHand();
            }
        }

    public void preFlop() throws Exception {
        // Fazer o small blind e o big blind apostarem
        Seat sbPlayer = players.get(smallBLind);
        Seat bbPlayer = players.get(bigBlind);
        sbPlayer.makeBet(smallBlindValue);
        bbPlayer.makeBet(bigBlindValue);
        pot += smallBlindValue;
        pot += bigBlindValue;

        int playerPosition2 = (bigBlind + 1) % players.size();
        Seat currentPlayer = players.get(playerPosition2);
        while(currentPlayer.hasToPlay()){
            if(!currentPlayer.isActive()){
                continue;
            }
            List<Actions> actions = new ArrayList<Actions>();
            if(hasRaised){
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
            }else if(playerPosition2 == smallBLind){
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
        printPlayersBets();
    }

    private void processPlayerAction(int seat, Actions action) throws Exception {
        if(action == Actions.FOLD){
            players.get(seat).setActive(false);
        }else if(action == Actions.CHECK){

        }else if(action == Actions.RAISE){
            // pegar o valor que foi aumentado, atualizar a max bet
            hasRaised = true;
            Seat player = players.get(seat);
            int value = player.bet();
            maxBet += value;
            pot += value;
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
            pot += valueToBet;
        }else if(action == Actions.BET){
            Seat player = players.get(seat);
            int value = player.bet();
            player.makeBet(value);
            pot += value;
        }

        Seat playerToDecide = players.get(seat);
        playerToDecide.setHasToPlay(false);
    }

    public void flop() throws Exception {
        // Virar três cartas na mesa e fazer a rodada de apostas novamente
        for(Seat seat : players){
            if(seat.isActive()) {
                seat.setHasToPlay(true);
            }
        }
        for(int i = 0; i < 3; i++){
            deck.shuffle();
            cards.add(deck.giveCard());
        }
        printCards();

        int playerPosition2 = (bigBlind + 1) % players.size();
        Seat currentPlayer = players.get(playerPosition2);
        while(currentPlayer.hasToPlay()){
            if(!currentPlayer.isActive()){
                continue;
            }
            List<Actions> actions = new ArrayList<Actions>();
            if(!hasRaised){
                actions.add(Actions.BET);
                actions.add(Actions.CHECK);
            }else{
                //
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
            }

            Actions action = currentPlayer.getAction(actions);
            processPlayerAction(playerPosition2, action);
            playerPosition2 = (playerPosition2 + 1) % players.size();
            currentPlayer = players.get(playerPosition2);
        }
    }

    public void turn() throws Exception {
        for(Seat seat : players){
            if(seat.isActive()) {
                seat.setHasToPlay(true);
            }
        }
        deck.shuffle();
        cards.add(deck.giveCard());
        printCards();

        int playerPosition2 = (bigBlind + 1) % players.size();
        Seat currentPlayer = players.get(playerPosition2);
        while(currentPlayer.hasToPlay()){
            if(!currentPlayer.isActive()){
                continue;
            }
            List<Actions> actions = new ArrayList<Actions>();
            if(!hasRaised){
                actions.add(Actions.RAISE);
                actions.add(Actions.CHECK);
            }else{
                //
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
            }

            Actions action = currentPlayer.getAction(actions);
            processPlayerAction(playerPosition2, action);
            playerPosition2 = (playerPosition2 + 1) % players.size();
            currentPlayer = players.get(playerPosition2);
        }
    }

    public void river() throws Exception {
        for(Seat seat : players){
            if(seat.isActive()) {
                seat.setHasToPlay(true);
            }
        }
        deck.shuffle();
        cards.add(deck.giveCard());
        printCards();

        int playerPosition2 = (bigBlind + 1) % players.size();
        Seat currentPlayer = players.get(playerPosition2);
        while(currentPlayer.hasToPlay()){
            if(!currentPlayer.isActive()){
                continue;
            }
            List<Actions> actions = new ArrayList<Actions>();
            if(!hasRaised){
                actions.add(Actions.BET);
                actions.add(Actions.CHECK);
            }else{
                //
                actions.add(Actions.RAISE);
                actions.add(Actions.CALL);
                actions.add(Actions.FOLD);
            }

            Actions action = currentPlayer.getAction(actions);
            processPlayerAction(playerPosition2, action);
            playerPosition2 = (playerPosition2 + 1) % players.size();
            currentPlayer = players.get(playerPosition2);
        }
    }

    private void printCards() {
        for(Card card : cards){
            card.print();
        }
    }

    private void printPlayersBets() {
        for (Seat player : players) {
            if (!player.isActive()) {
                System.out.printf("Jogador %s deu fold\n", player.getPlayerName());
            } else {
                System.out.printf("Jogador %s apostou %s\n", player.getPlayerName(), player.getBet());
            }

        }
    }

    public void revealCards() throws Exception {
        for(Seat seat : players){
            if(!seat.isActive()){
                continue;
            }

            System.out.printf("Jogador %s%n", seat.getPlayerName());
            seat.showHand();
            System.out.println("=============");
        }
        printCards();
    }

    public void judgeHands() {
        for(Seat seat : players){
            if(!seat.isActive()){
                continue;
            }

            ArrayList<Card> allCards = new ArrayList<Card>();
            allCards.addAll(seat.getCards());
            allCards.addAll(cards);
            var judge = new Judge(allCards);
            judge.testAllPossibilities();

            seat.setBestHand(judge.getBestHand(), judge.getBestHandScore());

            System.out.println("===========================================");
            System.out.printf("JOGADOR %s: %d\n", seat.getPlayerName(), judge.getBestHandScore());
            seat.showBestHand();

        }

        ArrayList<Seat> winners = new ArrayList<Seat>();

        int highScore = 0;
        for(Seat seat : players){
            if(!seat.isActive()){
                continue;
            }

            if(seat.getBestHandScore() > highScore){
                winners.clear();
                winners.add(seat);
                highScore = seat.getBestHandScore();
            }else if(seat.getBestHandScore() == highScore){
                winners.add(seat);
            }
        }

        int prizePerSeat = pot / winners.size();
        for(Seat player : winners){
            System.out.printf("%s ganhou %d fichas\n", player.getPlayerName(), prizePerSeat);
            player.addChips(prizePerSeat);
        }
    }
}
/*
TODO:
    Define high card
    Untie when players has same points but different high cards
    ALl-in
    Refactor utilizando alguns design patterns para melhorar o código
    Adicionar IA
    Adicionar multiplayer online

 */

