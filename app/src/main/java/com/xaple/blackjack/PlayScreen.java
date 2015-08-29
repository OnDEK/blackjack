package com.xaple.blackjack;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
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
    ImageView cardPic;

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

        newGame.startGame();
        replay();
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
        TextView tV = (TextView) findViewById(R.id.scoreTextView);
        newGame.scoreTextView = tV;
        tV.setText("Score: " + score);
        if(newGame.checkScore() != null){
            tV.setText("Bust!");

            hitButton.setEnabled(false);
            standButton.setEnabled(false);
            replayButton.setVisibility(View.VISIBLE);
        }
    }

    public void replay(View v) {
        replay();
    }

    public void replay () {
        newGame.replay();
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
        replayButton.setVisibility(View.INVISIBLE);

        for (Integer x : imageIDs) {
            cardPic = (ImageView) findViewById(x);
            cardPic.setImageDrawable(null);
        }

        newGame.drawCard(newGame.player);
        newGame.drawCard(newGame.player);
        changeScore(newGame.player.hand.GetHandScore());

        int i = 0;
        for ( Card c : newGame.player.hand.handList) {

            cardPic = (ImageView) findViewById(imageIDs[i]);
            cardPic.setImageResource(cardPics[c.GetCardValue()]);
            i++;
        }

        for ( Integer x: dealImgIDs) {

            cardPic = (ImageView) findViewById(x);
            cardPic.setImageDrawable(null);
        }

        Card temp = newGame.dealer.hand.GetCardAt(0);
        cardPic = (ImageView) findViewById(dealImgIDs[0]);
        cardPic.setImageResource(cardPics[temp.GetCardValue()]);

        if (newGame.player.hand.GetHandScore() == 21) {
            newGame.scoreTextView.setText("BLACKJACK!");
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
        }
        gameOverState();
    }

    public void gameOverState() {

        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        replayButton.setVisibility(View.VISIBLE);
    }


}
