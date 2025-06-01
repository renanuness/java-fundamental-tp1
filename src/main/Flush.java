package main;

import java.util.ArrayList;

public class Flush implements IHandEvaluator{
    @Override
    public int evaluateHand(ArrayList<Card> hand) {
        if(isFlush(hand)){
            return 60;
        }
        return 0;
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
