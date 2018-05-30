package com.juego.juan.juego;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Main5Activity extends AppCompatActivity {
private MediaPlayer sMusic,sElefante,sMono,sPajaro,sFestejo;
private ImageView imgElefante,imgMono,imgPajaro;
private Button btnNuevo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        musicaInit();
        imgElefante = (ImageView) findViewById(R.id.imgElefante);
        imgMono = (ImageView) findViewById(R.id.imgMono);
        imgPajaro = (ImageView) findViewById(R.id.imgPajaro);
        btnNuevo = (Button)findViewById(R.id.button2);
        tambalear();
        sFestejo.start();
        sMusic.start();

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(ventana);
            }
        });

        imgElefante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sElefante.start();
            }
        });

        imgMono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sMono.start();
            }
        });

        imgPajaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sPajaro.start();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void musicaInit() {

        sMusic = MediaPlayer.create(getApplicationContext(), R.raw.fin);
        sMusic.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sElefante = MediaPlayer.create(getApplicationContext(), R.raw.elefante);
        sElefante.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sMono = MediaPlayer.create(getApplicationContext(), R.raw.mono);
        sMono.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sPajaro = MediaPlayer.create(getApplicationContext(), R.raw.pajaro);
        sPajaro.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sFestejo = MediaPlayer.create(getApplicationContext(), R.raw.festejo);
        sFestejo.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }
    public void tambalear(){
        Animation rotarDerecha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotacions);

        Animation moverchango = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.traslacin_imgs);
        imgElefante.startAnimation(rotarDerecha);
        imgPajaro.startAnimation(rotarDerecha);
        imgMono.startAnimation(moverchango);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sMusic.stop();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sMusic.stop();
    }
}
