package main;

import java.util.ArrayList;

public class Four implements IHandEvaluator{

    @Override
    public int evaluateHand(ArrayList<Card> hand) {

        int equals = 0;
        for(int i = 0; i < 2; i++){
            Card currentCard = hand.get(i);
            equals = 0;
            for(int j = i+1; j < hand.size(); j++){
                if(hand.get(j).getValue() == currentCard.getValue()){
                    equals += 1;
                }

                if(equals == 3){
                    return 80;
                }
            }
        }

        return 0;
    }
}
