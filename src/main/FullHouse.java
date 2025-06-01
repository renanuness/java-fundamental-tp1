package main;

import java.util.ArrayList;

public class FullHouse implements IHandEvaluator{

    @Override
    public int evaluateHand(ArrayList<Card> hand) {
        if(hand.get(0).getValue() == hand.get(1).getValue()){
            if(hand.get(1).getValue() == hand.get(2).getValue()){
                if(hand.get(3).getValue() == hand.get(4).getValue()){
                    return 70;
                }
            }else if(hand.get(2).getValue() == hand.get(3).getValue() && hand.get(3).getValue() == hand.get(4).getValue()){
                return 70;
            }
        }

        return 0;
    }
}
