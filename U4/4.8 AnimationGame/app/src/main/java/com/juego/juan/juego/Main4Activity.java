package com.juego.juan.juego;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Main4Activity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        img = (ImageView) findViewById(R.id.imageView);
        musicaInit();
        rotar();
        mediaPlayer.start();
    }
    public void rotar(){
        Animation rotarDerecha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotacions);
        img.startAnimation(rotarDerecha);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    public void musicaInit(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.loop);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
}
}
