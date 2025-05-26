package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private static long seed = System.nanoTime();
    private List<Card> cards;

    public Deck(){
        cards = new ArrayList<Card>();
        for(Suits suit : Suits.values()){
            for(int j = 1; j <= 13; j++){
                Card card = new Card(suit, j);
                cards.add(card);
            }
        }
        shuffle();
    }


    public Card giveCard(){
        shuffle();
        return cards.removeFirst();
    }

    public void shuffle(){
        Random rand = new Random();
        rand.setSeed(seed++);
        if( seed == Long.MAX_VALUE){
            seed = 0;
        }
        Collections.shuffle(cards, rand);
        Collections.shuffle(cards);
        Collections.shuffle(cards, rand);
        Collections.shuffle(cards);
        Collections.shuffle(cards, rand);
        Collections.shuffle(cards);

    }
}
