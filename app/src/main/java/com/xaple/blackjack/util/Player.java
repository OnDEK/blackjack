package com.xaple.blackjack.util;

import android.widget.TextView;

/**
 * Created by Daniel on 3/15/15.
 */
public class Player {

    boolean busted = false;
    boolean turn = false;
    int bank = 0;
    int  currentBet = 0;
    public Hand hand = new Hand();

    void setBusted(boolean b) { busted = b; }

    boolean getBusted(){
        return busted;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int n) {
        bank = n;
    }

    public void addToBank(int n) {
        bank += n;
    }

    public void removeFromBank(int n) { bank -= n; }

    boolean getTurn() { return turn; }

    public void turnOver() {
        turn = false;
    }

    public void turnStart() {
        turn = true;
    }

    public void addBet(int n) {
        currentBet += n;
        removeFromBank(n);
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int n) {

        currentBet = n;
    }


}
