package com.xaple.blackjack.util;

import android.widget.TextView;

/**
 * Created by Daniel on 3/15/15.
 */
public class BlackJack {

    public Player dealer = new Player();
    public Player player = new Player();
    static Deck deck = new Deck();
    public TextView scoreTextView;


    public  void startGame(){
        player.hand.handList.clear();
        dealer.hand.handList.clear();
        deck.Shuffle(1);

        drawCard(dealer);
    }

    public void drawCard(Player p){
        Card newCard;
        newCard = deck.GetNextCard();
        p.hand.AddCard(newCard);

        checkScore();
    }

    public String checkScore(){
        if(player.hand.GetHandScore() > 21) {
            player.setBusted(true);
            return "Bust";
        }
        return null;
    }

    public void replay() {
        startGame();
    }

    public void dealerTurn(){
        while(dealer.hand.GetHandScore() < 17 && dealer.hand.GetHandScore() < player.hand.GetHandScore() ) {
            drawCard(dealer);
        }
        checkWinner();
    }

    public void checkWinner() {
        if (dealer.hand.GetHandScore() > player.hand.GetHandScore() && dealer.hand.GetHandScore() <= 21 ) {
            scoreTextView.setText("Dealer Wins!");
        }
        else if(dealer.hand.GetHandScore() == player.hand.GetHandScore()){
            scoreTextView.setText("Push!");
            player.addToBank(player.getCurrentBet());
        }
        else {
            scoreTextView.setText("Player Wins!");
            player.addToBank(player.getCurrentBet()*2);
        }
    }


}
