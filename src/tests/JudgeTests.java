package tests;

import main.Card;
import main.Judge;
import main.Suits;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class JudgeTests {

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

    }

    @Test
    void testStraightFlush(){
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.CLUBS, 13));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.CLUBS, 3));
        hand.add(new Card(Suits.CLUBS, 4));
        hand.add(new Card(Suits.CLUBS, 5));
        hand.add(new Card(Suits.CLUBS, 6));
        hand.add(new Card(Suits.CLUBS, 7));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(90, score);
    }


    @Test
    void testRoyalStraight(){
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.CLUBS, 8));
        hand.add(new Card(Suits.CLUBS, 11));
        hand.add(new Card(Suits.CLUBS, 1));
        hand.add(new Card(Suits.CLUBS, 13));
        hand.add(new Card(Suits.CLUBS, 7));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(100, score);
    }

    @Test
    void testFour() {
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.DIAMONDS, 10));
        hand.add(new Card(Suits.HEARTS, 11));
        hand.add(new Card(Suits.SPADES, 10));
        hand.add(new Card(Suits.CLUBS, 13));
        hand.add(new Card(Suits.HEARTS, 10));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(80, score);
    }

    @Test
    void testFullHouse() {
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.DIAMONDS, 10));
        hand.add(new Card(Suits.HEARTS, 11));
        hand.add(new Card(Suits.SPADES, 10));
        hand.add(new Card(Suits.CLUBS, 13));
        hand.add(new Card(Suits.HEARTS, 11));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(70, score);
    }

    @Test
    void testIsFlush(){
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.DIAMONDS, 10));
        hand.add(new Card(Suits.CLUBS, 11));
        hand.add(new Card(Suits.CLUBS, 2));
        hand.add(new Card(Suits.CLUBS, 13));
        hand.add(new Card(Suits.HEARTS, 11));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(60, score);
    }

    @Test
    void testStraight() {
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.DIAMONDS, 8));
        hand.add(new Card(Suits.HEARTS, 11));
        hand.add(new Card(Suits.SPADES, 1));
        hand.add(new Card(Suits.CLUBS, 13));
        hand.add(new Card(Suits.CLUBS, 7));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(50, score);
    }

    @Test
    void testThree() {
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.SPADES, 1));
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.DIAMONDS, 10));
        hand.add(new Card(Suits.HEARTS, 10));
        hand.add(new Card(Suits.CLUBS, 13));
        hand.add(new Card(Suits.CLUBS, 7));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(40, score);
    }


    @Test
    void testTwoPairs() {
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.SPADES, 1));
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.DIAMONDS, 11));
        hand.add(new Card(Suits.HEARTS, 10));
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 7));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(30, score);
    }

    @Test
    void testPair() {
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.SPADES, 1));
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.DIAMONDS, 11));
        hand.add(new Card(Suits.HEARTS, 10));
        hand.add(new Card(Suits.CLUBS, 4));
        hand.add(new Card(Suits.CLUBS, 7));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        assertEquals(20, score);
    }

    @Test
    void testHighCard() {
        var hand = new ArrayList<Card>();
        hand.add(new Card(Suits.SPADES, 1));
        hand.add(new Card(Suits.CLUBS, 12));
        hand.add(new Card(Suits.CLUBS, 10));
        hand.add(new Card(Suits.DIAMONDS, 11));
        hand.add(new Card(Suits.HEARTS, 8));
        hand.add(new Card(Suits.CLUBS, 4));
        hand.add(new Card(Suits.CLUBS, 7));

        var judge = new Judge(hand);
        judge.testAllPossibilities();
        int score = judge.getBestHandScore();

        var bestHand = judge.getBestHand();

        assertEquals(Suits.HEARTS, bestHand.get(0).getSuit());
        assertEquals(8, bestHand.get(0).getValue());

        assertEquals(Suits.CLUBS, bestHand.get(1).getSuit());
        assertEquals(10, bestHand.get(1).getValue());

        assertEquals(Suits.DIAMONDS, bestHand.get(2).getSuit());
        assertEquals(11, bestHand.get(2).getValue());

        assertEquals(Suits.CLUBS, bestHand.get(3).getSuit());
        assertEquals(12, bestHand.get(3).getValue());

        assertEquals(Suits.SPADES, bestHand.get(4).getSuit());
        assertEquals(1, bestHand.get(4).getValue());
        assertEquals(10, score);
    }
}
