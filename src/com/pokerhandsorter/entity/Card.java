package com.pokerhandsorter.entity;

public class Card implements Comparable<Card> {

  private final int cardValue;
  private final char suit;

  /* Assign numeric value to card Value (Ten, Jack, Queen & Queen)
  * To help in sorting and calculating the rank */
  public Card(String card) {
    char value = card.charAt(0);
    switch (value) {
      case 'T':
        this.cardValue = 10;
        break;

      case 'J':
        this.cardValue = 11;
        break;

      case 'Q':
        this.cardValue = 12;
        break;

      case 'K':
        this.cardValue = 13;
        break;

      case 'A':
        this.cardValue = 14;
        break;

      default:
        this.cardValue = Integer.parseInt("" + value);
    }
    this.suit = card.charAt(1);
  }

  @Override
  public int compareTo(Card cardToCompare) {
    int compareValue = cardToCompare.getValue();
    return this.cardValue - compareValue;
  }

  public String toString() {
    return String.valueOf(this.cardValue) + this.suit;
  }

  public char getSuit() {
    return this.suit;
  }

  public int getValue() {
    return this.cardValue;
  }
}
