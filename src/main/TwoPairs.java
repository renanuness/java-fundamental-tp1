package main;

import java.util.ArrayList;

public class TwoPairs implements IHandEvaluator{
    @Override
    public int evaluateHand(ArrayList<Card> hand) {
        int pairsCount = 0;
        int firstPairValue = 0;
        for(int i = 0; i<hand.size() -1; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                if (hand.get(i).getValue() == firstPairValue) {
                    continue;
                }
                pairsCount++;
                firstPairValue = hand.get(i).getValue();
                i++;
            }
        }
        if(pairsCount == 2) {
            return 30;
        }else if(pairsCount == 1) {
            return 20;
        }
        return 0;
    }
}
