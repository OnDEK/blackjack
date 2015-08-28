package com.xaple.blackjack.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Daniel on 3/15/15.
 */
public class Deck {

    ArrayList<Card> cardList = new ArrayList<Card>();
    Random objRnd = new Random();

    public void Shuffle( int numberOfDecks ){

        cardList.clear();

        int[] nCardCount = new int[52];

        for( int i=0; i < numberOfDecks*52; i++ ){

            int nCard;
            do{

                nCard = objRnd.nextInt(52);
            }
            while( nCardCount[nCard] >= numberOfDecks);

            nCardCount[nCard]++;
            cardList.add(new Card(nCard));
        }
    }

    public Card GetNextCard(){

        int nLastIndex = cardList.size() - 1;
        Card objCard = cardList.get(nLastIndex);
        cardList.remove(nLastIndex);
        return( objCard );
    }

    public int GetNumCardsInHand(){

        return( cardList.size() );
    }

}
