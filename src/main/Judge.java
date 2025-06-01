package main;

import java.util.ArrayList;

public class Judge {

    private ArrayList<Card> allCards;
    private ArrayList<Card> bestHand = new ArrayList<>();
    private int bestHandScore = 0;
    private IHandEvaluator[] evaluators;

    public Judge(ArrayList<Card> hand) {
        this.allCards = hand;
        evaluators = new IHandEvaluator[10];
        evaluators[0] = new StraightFlush();
        evaluators[1] = new Four();
        evaluators[2] = new FullHouse();
        evaluators[3] = new Flush();
        evaluators[4] = new Three();
        evaluators[5] = new TwoPairs();
        evaluators[6] = new HighCard();
    }

    // Criar um objeto para bestHand com o score da hand
    public ArrayList<Card> getBestHand() {
        return bestHand;
    }

    public int getBestHandScore() {
        return bestHandScore;
    }

    public void giveHandScore(ArrayList<Card> hand) {
        // Testar todas as possibilidades

        hand.sort((o1, o2) -> {
            if(o1.getValue() > o2.getValue()){
                return 1;
            }else if(o1.getValue() < o2.getValue()){
                return -1;
            }
            return 0;
        });

        for(int i = 0; i < 6; i++){
            int handScore = evaluators[i].evaluateHand(hand);
            if(handScore > bestHandScore) {
                bestHandScore = handScore;
                bestHand = hand;
            }
        }
    }

    public void testAllPossibilities(){
        // [3, 9, 5, 8, 7, 11, 13]
        // 3
        bestHandScore = 10;
        allCards.sort((o1, o2) -> {
            if(o1.getValue() == 1 && o2.getValue() != 1){
                return 1;
            }else if(o1.getValue() != 1 && o2.getValue() == 1){
                return -1;
            }
            if(o1.getValue() > o2.getValue()){
                return 1;
            }else if(o1.getValue() < o2.getValue()){
                return -1;
            }
            return 0;
        });
        bestHand.add(allCards.get(2));
        bestHand.add(allCards.get(3));
        bestHand.add(allCards.get(4));
        bestHand.add(allCards.get(5));
        bestHand.add(allCards.get(6));

        int counter = 0;
        for(int i = 0; i< allCards.size(); i++){ // 7
            for(int j = i+1; j< allCards.size(); j++){
                for(int k = j+1; k< allCards.size(); k++){
                    for(int l = k+1; l< allCards.size(); l++){
                        for(int m = l+1; m< allCards.size(); m++){
                            ArrayList<Card> selectedCards = new ArrayList<>();
                            selectedCards.add(allCards.get(i));
                            selectedCards.add(allCards.get(j));
                            selectedCards.add(allCards.get(k));
                            selectedCards.add(allCards.get(l));
                            selectedCards.add(allCards.get(m));
                            giveHandScore(selectedCards);
                        }
                    }
                }
            }
        }
    }
}

