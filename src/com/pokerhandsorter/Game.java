package com.pokerhandsorter;

import com.pokerhandsorter.constants.Constants;
import com.pokerhandsorter.evaluation.Hand;
import com.pokerhandsorter.exceptions.InvalidHandException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Game {

  public static void main(String[] args) {
    int playerOneScore = 0;
    int playerTwoScore = 0;

    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      // main loop for piping through stdin
      while (true) {
        String input = br.readLine();
        if (input == null) {
          break;
        }
        // Simple input validation using regex
        if (input.matches("(?:[2-9TJQKA][SCHD] ){9}[2-9TJQKA][SCHD]")) {
          String[] cards = input.split(" ");
          String[] handOneStr = Arrays.copyOfRange(cards, 0, 5);
          String[] handTwoStr = Arrays.copyOfRange(cards, 5, 10);

          Hand handOne = new Hand(handOneStr);
          Hand handTwo = new Hand(handTwoStr);

          handOne.evaluate();
          handTwo.evaluate();
          int res = winner(handOne, handTwo);
          if (res == 1) {
            playerOneScore++;
          } else if (res == 2) {
            playerTwoScore++;
          } else {
            System.out.println(Constants.TIE_MESSAGE);
          }
        } else {
          throw new InvalidHandException(Constants.INVALID_HAND);
        }
      }
      displayWinner(playerOneScore,playerTwoScore);
    } catch (IOException | InvalidHandException e ) {
      e.printStackTrace();
      if (playerOneScore > 0 || playerTwoScore > 0 ) {
        displayWinner(playerOneScore,playerTwoScore);
      }
    }
  }

  /* Method to return which Play wins the hand.
    1 - Player One Wins
    2 - Player Two Wins
   -1 - Tie */

  public static int winner(Hand hand1, Hand hand2) {
    if (hand1.getHandCategory().getValue() > hand2.getHandCategory().getValue()) {
      return 1;
    } else if (hand1.getHandCategory().getValue() < hand2.getHandCategory().getValue()) {
      return 2;
    } else if (hand1.getHandValue() > hand2.getHandValue()) {
      return 1;
    } else if (hand1.getHandValue() < hand2.getHandValue()) {
      return 2;
    } else {
      // Check for tie-break
      for (int i = 4; i >= 0; i--) {
        if (hand1.getCard(i).getValue() > hand2.getCard(i).getValue()) {
          return 1;
        } else if (hand1.getCard(i).getValue() < hand2.getCard(i).getValue()) {
          return 2;
        }
      }
      // There is a Tie
      return -1;
    }
  }

  // Display score of Players for each round
  public static void displayWinner(int playerOneScore, int playerTwoScore) {
    System.out.println("Player One: " + playerOneScore + " hands");
    System.out.println("Player Two: " + playerTwoScore + " hands");
  }
}
