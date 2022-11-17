package com.pokerhandsorter.evaluation;

import com.pokerhandsorter.constants.Constants;
import com.pokerhandsorter.entity.Card;
import com.pokerhandsorter.enums.HandCategory;
import java.util.Arrays;
import java.util.HashMap;

public class Hand {
  public Card[] cards;

  public HandCategory category;

  public Integer handValue;

  public Hand(String[] strArr) {
    if (strArr.length != 5) {
      System.out.println(Constants.INVALID_HAND);
    } else {
      Card[] cards = new Card[5];
      for (int i = 0; i < 5; i++) {
        cards[i] = new Card(strArr[i]);
      }
      this.cards = cards;
    }
  }

  public void sortCards() {
    Arrays.sort(this.cards);
  }

  public Card getCard(int index) {
    return cards[index];
  }

  public HandCategory getHandCategory() {
    return this.category;
  }

  public Integer getHandValue() {
    return this.handValue;
  }

  public String toString() {
    StringBuilder str = new StringBuilder();
    for (Card card : this.cards) {
      str.append(card.toString()).append(" ");
    }
    if(str.length() > 0)
      str.append("(").append(this.getHandCategory().getDesc()).append(")");
    return str.toString();
  }

  // Method to evaluate the winner between too hands
  public void evaluate() {
    sortCards();
    if (this.allSameSuit() != -1 && this.straight() != -1) {
      if (this.getCard(0).getValue() == 10) {
        this.category = HandCategory.ROYAL_FLUSH;
        this.handValue = 9999;
      } else {
        this.category = HandCategory.STRAIGHT_FLUSH;
      }
      return;
    }

    if (this.getSameCard(4) != -1) {
      this.category = HandCategory.FOUR_OF_A_KIND;
      return;
    }

    if (this.fullHouse() != -1) {
      this.category = HandCategory.FULL_HOUSE;
      return;
    }

    if (this.allSameSuit() != -1) {
      this.category = HandCategory.FLUSH;
      return;
    }

    if (this.straight() != -1) {
      this.category = HandCategory.STRAIGHT;
      return;
    }

    if (this.getSameCard(3) != -1) {
      this.category = HandCategory.THREE_OF_A_KIND;
      return;
    }

    if (this.twoPairs() != -1) {
      this.category = HandCategory.TWO_PAIRS;
      return;
    }

    if (this.getSameCard(2) != -1) {
      this.category = HandCategory.ONE_PAIR;
      return;
    }

    this.handValue = this.getCard(4).getValue();
    this.category = HandCategory.HIGH_CARD;
  }

  // Get Pair, Three of a kind and Four of a kind with the card value
  private int getSameCard(int noOfSameCard) {
    HashMap<Integer,Integer> frequency = (countFrequency(this.cards));
    for (HashMap.Entry<Integer, Integer> e : frequency.entrySet()) {
      if(e.getValue() == noOfSameCard) {
        this.handValue = e.getKey();
        return e.getKey();
      }
    }
    return -1;
  }

  private int twoPairs(){
    int total = 0, pairCount = 0;
    HashMap<Integer,Integer> frequency = (countFrequency(this.cards));
    for (HashMap.Entry<Integer, Integer> e : frequency.entrySet()) {
      if(e.getValue() == 2) {
        total += e.getKey();
        pairCount ++;
      }
    }
    if (pairCount == 2) {
      this.handValue = total;
      return total;
    }
    return -1;
  }

  private int fullHouse() {
    int getPair = this.getSameCard(2);
    int getThreeOfSameKind = this.getSameCard(3);
    if ( getPair != -1 && getThreeOfSameKind != -1)  {
      int total = getPair + getThreeOfSameKind;
      this.handValue = total;
      return total;
    } else {
      return -1;
    }
  }

  private int allSameSuit() {

    char prev = this.cards[0].getSuit();
    int total = this.cards[0].getValue();

    for (int i = 1; i < 5; i++) {
      if (this.cards[i].getSuit() != prev) {
        return -1;
      }
      total += this.cards[i].getValue();
      prev = this.cards[i].getSuit();
    }
    this.handValue = total;
    return total;
  }

  private int straight() {

    int prev = this.cards[0].getValue();
    int total = prev;
    for (int i = 1; i < 5; i++) {
      if (this.cards[i].getValue() != prev + 1) {
        return -1;
      }
      prev = this.cards[i].getValue();
      total += 1;
    }
    this.handValue = total;
    return total;
  }

  // Method to count the occurrence of a card value
  private static HashMap<Integer, Integer> countFrequency(Card[] card) {
    int n = card.length;
    boolean[] visited = new boolean[n];
    HashMap<Integer, Integer> map = new HashMap<>();

    Arrays.fill(visited, false);

    // Traverse through array elements and count frequencies
    for (int i = 0; i < n; i++) {

      // Skip this element if already processed
      if (visited[i])
        continue;

      // Count frequency
      int count = 1;
      for (int j = i + 1; j < n; j++) {
        if (card[i].getValue() == card[j].getValue()) {
          visited[j] = true;
          count++;
        }
      }
      // Store the frequency of value in HashMap
      map.put(card[i].getValue(), count);
    }
    return map;
  }
}
