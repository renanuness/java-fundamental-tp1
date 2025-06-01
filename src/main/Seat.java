package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seat {
    private Player player;
    private ArrayList<Card> cards;
    private boolean active;
    private int bet;
    private Actions action;
    private boolean hasToPlay = false;

    public Seat(Player player){
        this.player = player;
        this.cards = new ArrayList<Card>();
        bet = 0;
    }

    public void giveCard(Card card) throws Exception {
        if(cards.size() == 2){
            throw new Exception();
        }
        cards.add(card);
    }

    public void showHand(){
        for (Card card : cards) {
            card.print();
        }
    }

    public String getPlayerName(){
        return player.getName();
    }

    public Actions getAction(List<Actions> actions){
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Defina sua ação %s:\n", getPlayerName());
            System.out.printf("Você já apostou: %s\n", bet);

            for(Actions action : actions){
                if(action == Actions.CALL){
                    System.out.println("Call - C");
                }else if(action == Actions.RAISE){
                    System.out.println("Raise - R");
                }else if(action == Actions.FOLD) {
                    System.out.println("Fold - F");
                }else if(action == Actions.CHECK){
                    System.out.println("Check - X");
                }else if(action == Actions.BET){
                    System.out.println("Bet - B");
                }
            }

            String actionStr = scanner.nextLine();
            Actions chooseAction = Actions.NONE;
            if (actionStr.equalsIgnoreCase("r")) {
                chooseAction = Actions.RAISE;
            } else if (actionStr.equalsIgnoreCase("f")) {
                chooseAction = Actions.FOLD;
            } else if (actionStr.equalsIgnoreCase("c")) {
                chooseAction = Actions.CALL;
            }else if (actionStr.equalsIgnoreCase("b")) {
                chooseAction = Actions.BET;
            }else if (actionStr.equalsIgnoreCase("x")) {
                chooseAction = Actions.CHECK;
            }

            if(actions.contains(chooseAction)){
                return chooseAction;
            }
        }while(true);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int bet(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quanto você quer apostar?");
        int betValue = scanner.nextInt();
        return betValue;
    }

    public int bet(int minValue){
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Quanto você quer apostar?");
            int betValue = scanner.nextInt();
            if(betValue >= minValue) {
                return betValue;
            }
            System.out.printf("Você precisa apostar pelo menos %d\n", minValue);
        }
    }

    public void makeBet(int value) {
        bet += value;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public boolean hasToPlay() {
        return hasToPlay;
    }

    public void setHasToPlay(boolean hasToPlay) {
        this.hasToPlay = hasToPlay;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}
