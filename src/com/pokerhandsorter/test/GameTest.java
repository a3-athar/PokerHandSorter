package com.pokerhandsorter.test;

import com.pokerhandsorter.Game;
import com.pokerhandsorter.evaluation.Hand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

  @Test
  void chooseWinner() {

    Hand handOne = new Hand("9C 9D 8D 7C 3C".split(" "));
    Hand handTwo = new Hand("2S KD TH 9H 8H".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    assertEquals(1, Game.winner(handOne, handTwo));
    System.out.println("Unit test of the method winner() ");
    System.out.println("Player 1 won with ONE PAIR. PASSED.\n");

    handOne = new Hand("9C 9D 8D 7C TC".split(" "));
    handTwo = new Hand("9H 9S 8C 7D 3D".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne.toString() + " " + handTwo.toString());
    assertEquals(1, Game.winner(handOne, handTwo));
    System.out.println("Player 1 won with ONE PAIR - HIGH CARD. PASSED.\n");

    handOne = new Hand("2H 7H 2D 3S 3D".split(" "));
    handTwo = new Hand("9H 8C 6S 7D 9D".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne.toString() + " " + handTwo.toString());
    assertEquals(1, Game.winner(handOne, handTwo));
    System.out.println("Player 1 won with TWO PAIR. PASSED.\n");

    handOne = new Hand("2H 7H 2D 3S 3D".split(" "));
    handTwo = new Hand("2H 7H 2D 3S 2S".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne.toString() + " " + handTwo.toString());
    assertEquals(2, Game.winner(handOne, handTwo));
    System.out.println("Player 2 won with THREE OF A KIND. PASSED.\n");

    handOne = new Hand("6H 2D 3S 4S 5S".split(" "));
    handTwo = new Hand("2H 7H 2D 3S 2S".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne.toString() + " " + handTwo.toString());
    assertEquals(1, Game.winner(handOne, handTwo));
    System.out.println("Player 1 won with STRAIGHT. PASSED.\n");

    handOne = new Hand("QC TC 7C AC KC".split(" "));
    handTwo = new Hand("2H 7H 2D 3S 2S".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne + " " + handTwo);
    assertEquals(1, Game.winner(handOne, handTwo));
    System.out.println("Player 1 won with FLUSH. PASSED.\n");

    handOne = new Hand("QC TC 7C AC KC".split(" "));
    handTwo = new Hand("2H 3H 2D 3S 3D".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne + " " + handTwo);
    assertEquals(2, Game.winner(handOne, handTwo));
    System.out.println("Player 1 won with FULL HOUSE. PASSED.\n");

    handOne = new Hand("TC 2C JC 7C 3C".split(" "));
    handTwo = new Hand("9H 9C 9S 7D 9D".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne + " " + handTwo.toString());
    assertEquals(2, Game.winner(handOne, handTwo));
    System.out.println("Player 2 won with FOUR OF A KIND. PASSED.\n");

    handOne = new Hand("QC TC 7C AC KC".split(" "));
    handTwo = new Hand("TH JH QH KH AH".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne + " " + handTwo);
    assertEquals(2, Game.winner(handOne, handTwo));
    System.out.println("Player 1 won with STRAIGHT FLUSH . PASSED.\n");

    handOne = new Hand("9C 9D 8D 7C 3C".split(" "));
    handTwo = new Hand("9H 9S 8C 7D 3D".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne + " " + handTwo);
    assertEquals( -1, Game.winner(handOne, handTwo));
    System.out.println("TIE. PASSED.\n");

    handOne = new Hand("TC 2C TS 2S TD".split(" "));
    handTwo = new Hand("AH KH JH QH TH".split(" "));
    handOne.evaluate();
    handTwo.evaluate();
    System.out.println(handOne.toString() + " " + handTwo.toString());
    assertEquals(2, Game.winner(handOne, handTwo));
    System.out.println("Player 2 won with ROYAL FLUSH. PASSED.\n");
  }
}