package com.example.applitchai;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mPlayer;
    private boolean isPrepared = false;
    private Button sound1;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        url = "http://sp.athanyl.net/oui1.mp3";
        sound1 = findViewById(R.id.button_1);

        sound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPrepared && !mPlayer.isPlaying()){
                    mPlayer.start();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        mPlayer = new MediaPlayer();
        readSound(R.raw.oui1);

        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isPrepared = true;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        isPrepared = false;
        mPlayer.release();
        mPlayer = null;
    }

    private void readSound(int resId){
        if(mPlayer != null){
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer.create(this, resId);
        mPlayer.start();
    }
}
