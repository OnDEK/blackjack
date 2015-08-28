package com.xaple.blackjack.util;

/**
 * Created by Daniel on 3/15/15.
 */
public class Player {

    boolean busted = false;
    boolean turn = false;
    int bank = 0;
    public Hand hand = new Hand();

    void setBusted(boolean b) {
        busted = b;
    }
    boolean getBusted(){
        return busted;
    }

    int getBank() {
        return bank;
    }

    void setBank(int n) {
        bank = n;
    }

    void addToBank(int n) {
        bank += n;
    }

    boolean getTurn() {
        return turn;
    }

    public void turnOver() {
        turn = false;
    }

    public void turnStart() {
        turn = true;
    }

}
