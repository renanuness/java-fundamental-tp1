package tests;

import main.Card;
import main.Judge;
import main.Suits;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class JudgeTests {

    @Test
    void testIsFlush(){
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.CLUBS, 1));
        hand.add(new Card(Suits.CLUBS, 2));
        hand.add(new Card(Suits.CLUBS, 3));
        hand.add(new Card(Suits.CLUBS, 4));
        hand.add(new Card(Suits.CLUBS, 5));
        hand.add(new Card(Suits.CLUBS, 6));
        hand.add(new Card(Suits.CLUBS, 7));
        var judge = new Judge(hand);

        assertTrue(judge.isFlush());
    }

    @Test
    void printAllPossibilities(){
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.CLUBS, 1));
        hand.add(new Card(Suits.CLUBS, 2));
        hand.add(new Card(Suits.CLUBS, 3));
        hand.add(new Card(Suits.CLUBS, 4));
        hand.add(new Card(Suits.CLUBS, 5));
        hand.add(new Card(Suits.CLUBS, 6));
        hand.add(new Card(Suits.CLUBS, 7));
        var judge = new Judge(hand);

        judge.testAllPossibilities();

    }
}
