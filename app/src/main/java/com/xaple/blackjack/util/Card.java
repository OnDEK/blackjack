package com.xaple.blackjack.util;

/**
 * Created by Daniel on 3/15/15.
 */
public class Card {

    final static public int TWO = 0;
    final static public int THREE = 1;
    final static public int FOUR = 2;
    final static public int FIVE = 3;
    final static public int SIX = 4;
    final static public int SEVEN = 5;
    final static public int EIGHT = 6;
    final static public int NINE = 7;
    final static public int TEN = 8;
    final static public int JACK = 9;
    final static public int QUEEN = 10;
    final static public int KING = 11;
    final static public int ACE = 12;

    final static public int CLUBS = 0;
    final static public int DIAMONDS = 1;
    final static public int HEARTS = 2;
    final static public int SPADES = 3;

    int cardValue = 0;
    public Card( int cardValue ){

        this.cardValue = cardValue;
    }

    public void SetCardValue( int nCardValue ){

        cardValue = nCardValue;
    }

    public int GetCardValue(){

        return( cardValue );
    }

    public int GetSuit(){

        return( cardValue / 13 );
    }

    public int GetRank(){

        return( cardValue % 13 );
    }

    public static String[] strRanks = {
                    "Two", "Three", "Four", "Five", "Six", "Seven",
                    "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"
            };

    public static String[] strSuits = {
                    "Clubs", "Diamonds", "Hearts", "Spades"
            };

    public void Display(){

        int nRank = GetRank();
        int nSuit = GetSuit();

        System.out.print( strRanks[nRank] + " of " + strSuits[nSuit]);
    }

    public String GetDisplayString(){

        int nRank = GetRank();
        int nSuit = GetSuit();

        return( strRanks[nRank] + " of " + strSuits[nSuit]);
    }


}
