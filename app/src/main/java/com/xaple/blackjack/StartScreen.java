package com.xaple.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class StartScreen extends Activity {

    MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        music = MediaPlayer.create(StartScreen.this, R.raw.start_music);
        if(!music.isPlaying())
            music.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openPlayActivity(View v){

        Intent intent = new Intent(this, PlayScreen.class);
        startActivity(intent);
    }

    public void muteMusic(View v){

        if(music.isPlaying()) {
            music.pause();
        }
        else
            music.start();
    }

    protected void onPause() {
        super.onPause();
        music.pause();
    }

    protected void onResume() {
        super.onResume();
        if(!music.isPlaying())
            music.start();
    }

}
