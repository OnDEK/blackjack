package com.xaple.blackjack.util;

import java.util.ArrayList;

/**
 * Created by Daniel on 3/15/15.
 */
public class Hand {

    public ArrayList<Card> handList = new ArrayList<Card>();

    public int GetHandScore()
    {
        int numAces = 0;
        int handScore = 0;

        for( int i=0; i< handList.size(); i++ ){

            int curCardRank = handList.get(i).GetRank();
            if( curCardRank <= Card.NINE){

                handScore += ( curCardRank + 2 );
            }
            else if( curCardRank == 12 ){

                handScore += 11;
                numAces++;
            }
            else{

                handScore += 10;
            }
        }

        while( handScore > 21 && numAces > 0 ){

            handScore -= 10;
            numAces--;
        }

        return( handScore );
    }

    public int GetNumCardsInHand(){

        return( handList.size());
    }

    public Card GetCardAt( int nIndex ){

        try{

            return( handList.get(nIndex));
        }
        catch(Exception ex){

            return( null );
        }
    }

    public void AddCard( Card objCard ){

        handList.add(objCard);
    }

    public void resetScore() {

    }

}
