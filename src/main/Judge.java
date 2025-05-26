package main;

import java.util.ArrayList;

public class Judge {

    private ArrayList<Card> hand;
    private ArrayList<Card> bestHand;

    public Judge(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public void giveHandScore() {


    }

    public void testAllPossibilities(){
        // [3, 9, 5, 8, 7, 11, 13]
        // 3
        int counter = 0;
        for(int i = 0; i<hand.size(); i++){ // 7
            for(int j = i+1; j<hand.size(); j++){
                for(int k = j+1; k<hand.size(); k++){
                    for(int l = k+1; l<hand.size(); l++){
                        for(int m = l+1; m<hand.size(); m++){
                            System.out.printf("Teste numero: %d\n", ++counter);
                            hand.get(i).print();
                            hand.get(j).print();
                            hand.get(k).print();
                            hand.get(l).print();
                            hand.get(m).print();
                            System.out.println("======================================");
                        }
                    }
                }
            }
        }
    }

    private void evaluateHand(ArrayList<Card> hand){

    }

    private boolean isStraightFlush(){
        if(!isFlush()){
            return false;
        }

        // 3 9 5 8 7 11 13 sem ordem
        // 3 5 7 8 9 11 13
        return true;
    }

    public boolean isFlush(){
        Suits suit = hand.getFirst().getSuit();
        for (int i = 1; i < hand.size(); i++) {
            if(hand.get(i).getSuit() != suit){
                return false;
            }
        }
        return true;
    }
}

// Descobrir qual a melhor mão do usuario
// Receber 7 cartas. descobrir o melhor jogo possível e devolver

// Realizar comparação entre as mãos dos usuários


// Royal straight flush -> 10 J Q K A todos do mesmo naipe
// Straight flush -> Sequencia do mesmo naipe
// Four of a kind
// Full house -> 2 2 3 3 3
// Flush -> todas do mesmo naipe
// Straight -> sequencia de qualquer naipe
// Three of a kind
// Two pair
// Pair
// High Card
