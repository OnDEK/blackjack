package com.xaple.blackjack;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.xaple.blackjack.util.BlackJack;
import com.xaple.blackjack.util.Card;

/**
 * Created by daniel on 3/15/15.
 */
public class PlayScreen extends Activity {

    int[] cardPics = {R.drawable.twoclub, R.drawable.threeclub, R.drawable.fourclub, R.drawable.fiveclub, R.drawable.sixclub,
            R.drawable.sevenclub, R.drawable.eightclub, R.drawable.nineclub, R.drawable.tenclub, R.drawable.jackclub,
            R.drawable.queenclub, R.drawable.kingclub, R.drawable.aceclub, R.drawable.twodiamond, R.drawable.threediamond,
            R.drawable.fourdiamond, R.drawable.fivediamond, R.drawable.sixdiamond, R.drawable.sevendiamond, R.drawable.eightdiamond,
            R.drawable.ninediamond, R.drawable.tendiamond, R.drawable.jackdiamond, R.drawable.queendiamond, R.drawable.kingdiamond,
            R.drawable.acediamond, R.drawable.twoheart, R.drawable.threeheart, R.drawable.fourheart, R.drawable.fiveheart, R.drawable.sixheart,
            R.drawable.sevenheart, R.drawable.eightheart, R.drawable.nineheart, R.drawable.tenheart, R.drawable.jackheart, R.drawable.queenheart,
            R.drawable.kingheart, R.drawable.aceheart, R.drawable.twospade, R.drawable.threespade, R.drawable.fourspade, R.drawable.fivespade,
            R.drawable.sixspade, R.drawable.sevenspade, R.drawable.eightspade, R.drawable.ninespade, R.drawable.tenspade, R.drawable.jackspade,
            R.drawable.queenspade, R.drawable.kingspade, R.drawable.acespade};

    int[] imageIDs = {R.id.cardPic0,R.id.cardPic1,R.id.cardPic2,R.id.cardPic3,R.id.cardPic4,
            R.id.cardPic5,R.id.cardPic6};
    int[] dealImgIDs = {R.id.dealerCard0,R.id.dealerCard1,R.id.dealerCard2,R.id.dealerCard3,
            R.id.dealerCard4};

    BlackJack newGame = new BlackJack();

    Button hitButton;
    Button standButton;
    Button replayButton;
    Button betButton;
    ImageView cardPic;
    ImageButton chip_5;
    ImageButton chip_25;
    ImageButton chip_100;
    ImageButton chip_500;

    TextView betText;
    TextView bankText;
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.play_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);

        hitButton = (Button) findViewById(R.id.hitButton);
        standButton = (Button) findViewById(R.id.standButton);
        replayButton = (Button) findViewById(R.id.replayButton);
        betButton = (Button) findViewById(R.id.betButton);

        betText = (TextView) findViewById(R.id.betTextView);
        bankText = (TextView) findViewById(R.id.bankTextView);
        statusText = (TextView) findViewById(R.id.scoreTextView);

        newGame.startGame();

        newGame.player.setBank(10000);

        bettingPhase();
    }

    public void drawCard(View v){
        //cardList = (TextView) findViewById(R.id.cardListView);
        newGame.drawCard(newGame.player);
        changeScore(newGame.player.hand.GetHandScore());

        int i = 0;
        for ( Card c : newGame.player.hand.handList) {

            cardPic = (ImageView) findViewById(imageIDs[i]);
            cardPic.setImageResource(cardPics[c.GetCardValue()]);
            i++;
        }
    }

    public void changeScore(int score){
        newGame.scoreTextView = statusText;
        statusText.setText("");
        if(newGame.checkScore() != null){
            statusText.setText("Bust!");

            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            replayButton.setVisibility(View.VISIBLE);
        }
    }

    public void replay(View v) {
        clearCardImages();
        bettingPhase();
    }

    private void bettingPhase() {
        betText.setText("Bet");
        statusText.setText("Place Your Bet!");
        newGame.player.setCurrentBet(0);

        replayButton.setVisibility(View.INVISIBLE);


        chip_5 = (ImageButton) findViewById(R.id.chip_5);
        chip_25 = (ImageButton) findViewById(R.id.chip_25);
        chip_100 = (ImageButton) findViewById(R.id.chip_100);
        chip_500 = (ImageButton) findViewById(R.id.chip_500);

        chip_5.setEnabled(true);
        chip_25.setEnabled(true);
        chip_100.setEnabled(true);
        chip_500.setEnabled(true);

        hitButton.setEnabled(false);
        standButton.setEnabled(false);

        betButton.setEnabled(true);
        betButton.setVisibility(View.VISIBLE);
    }

    public void placeBet(View v) {
        betButton.setEnabled(false);
        betButton.setVisibility(View.INVISIBLE);
        replay();
    }

    public void replay () {

        chip_5.setEnabled(false);
        chip_25.setEnabled(false);
        chip_100.setEnabled(false);
        chip_500.setEnabled(false);

        newGame.replay();
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
        replayButton.setVisibility(View.INVISIBLE);

        newGame.drawCard(newGame.player);
        newGame.drawCard(newGame.player);
        changeScore(newGame.player.hand.GetHandScore());

        int i = 0;
        for ( Card c : newGame.player.hand.handList) {

            cardPic = (ImageView) findViewById(imageIDs[i]);
            cardPic.setImageResource(cardPics[c.GetCardValue()]);
            i++;
        }

        Card temp = newGame.dealer.hand.GetCardAt(0);
        cardPic = (ImageView) findViewById(dealImgIDs[0]);
        cardPic.setImageResource(cardPics[temp.GetCardValue()]);

        if (newGame.player.hand.GetHandScore() == 21) {
            newGame.scoreTextView.setText("BLACKJACK!");
            newGame.player.addToBank(newGame.player.getCurrentBet()*(5/2));
            gameOverState();
        }
    }

    public void callStand(View v) {
        newGame.player.turnOver();
        newGame.dealerTurn();
        int i = 0;
        for ( Card c : newGame.dealer.hand.handList) {

            cardPic = (ImageView) findViewById(dealImgIDs[i]);
            cardPic.setImageResource(cardPics[c.GetCardValue()]);
            i++;
            if (i > 5) break;
        }
        gameOverState();
    }

    public void gameOverState() {

        bankText.setText("Bank\n" + newGame.player.getBank());
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        replayButton.setVisibility(View.VISIBLE);
    }


    public void addBet(View view) {


        switch( view.getTag().toString() ) {
            case "5":
                if(newGame.player.getBank() < 5) break;

                newGame.player.addBet(5);
                betText.setText("Bet\n" + newGame.player.getCurrentBet());
                break;
            case "25":
                if(newGame.player.getBank() < 25) break;

                newGame.player.addBet(25);
                betText.setText("Bet\n" + newGame.player.getCurrentBet());
                break;
            case "100":
                if(newGame.player.getBank() < 100) break;

                newGame.player.addBet(100);
                betText.setText("Bet\n" + newGame.player.getCurrentBet());
                break;
            case "500":
                if(newGame.player.getBank() < 500) break;

                newGame.player.addBet(500);
                betText.setText("Bet\n" + newGame.player.getCurrentBet());
                break;
        }
        bankText.setText("Bank\n" + newGame.player.getBank());

    }
    void clearCardImages() {

        for (Integer x : imageIDs) {
            cardPic = (ImageView) findViewById(x);
            cardPic.setImageDrawable(null);
        }

        for ( Integer x: dealImgIDs) {

            cardPic = (ImageView) findViewById(x);
            cardPic.setImageDrawable(null);
        }
    }
}
