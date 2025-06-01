package main;

import java.util.ArrayList;

public class StraightFlush implements IHandEvaluator{


    public int evaluateHand(ArrayList<Card> hand) {
            boolean isRoyal = false;
            boolean isFlush = false;
            if(isFlush(hand)){
                isFlush = true;
            }

            int lastValue = hand.getFirst().getValue();

            boolean canBeRoyal = false;
            if(lastValue == 1 && hand.getLast().getValue() == 13){
                canBeRoyal = true;
                hand.add(new Card(hand.getFirst().getSuit(), 14));
            }
            // i: 1 -> 4
            for(int i = 1; i < hand.size(); i++){
                int currentValue = hand.get(i).getValue();

                if (lastValue+1 == currentValue){
                    lastValue = currentValue;

                    continue;
                }

                if(i == 1 && canBeRoyal){
                    isRoyal = true;
                    lastValue = currentValue;
                    continue;
                }
                return 0;
            }

            if(isFlush && isRoyal){
                return 100;
            }
            if(isFlush){
                return 90;
            }
            return 50;
        }

    private boolean isFlush(ArrayList<Card> hand){
        Suits suit = hand.getFirst().getSuit();
        for (int i = 1; i < hand.size(); i++) {
            if(hand.get(i).getSuit() != suit){
                return false;
            }
        }
        return true;
    }
}
