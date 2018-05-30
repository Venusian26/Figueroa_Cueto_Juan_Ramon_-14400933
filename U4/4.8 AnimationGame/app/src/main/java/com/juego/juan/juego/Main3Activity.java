package com.juego.juan.juego;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Main3Activity extends AppCompatActivity {
private ImageView imgOver;
private MediaPlayer sOver;
private Button btnNuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        musicLose();
        imgOver = (ImageView) findViewById(R.id.imgOver);
        btnNuevo = (Button) findViewById(R.id.button);
        AnimationDrawable animOver = (AnimationDrawable) ContextCompat.getDrawable(this, R.drawable.aniamcion_perder);
        imgOver.setImageDrawable(animOver);
        animOver.start();

       sOver.start();

       btnNuevo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent ventana = new Intent(getApplicationContext(),Main2Activity.class);
               startActivity(ventana);
           }
       });
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
public void Animover(){


}
    public void musicLose(){
        sOver = MediaPlayer.create(getApplicationContext(),R.raw.lose);
        sOver.setAudioStreamType(AudioManager.STREAM_MUSIC);

    }


}
