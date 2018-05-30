package com.juego.juan.juego;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private TextView txtNumero, txtNumeroIntroduc, txtPuntos,txtVidas,txtNivel;
    private Button btnVerificar, btnComenzar;
    private MediaPlayer sGood,sComenzar,sFondo,Sbuu,sCoin,sWo,sMusic;
    private ImageView imgCoin,imgPerro,imgGato;
    ConstraintLayout layout;
    int puntos,vidas,nivel;
    //--------------------ANIMACIONES------------------

    //-----------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //referencias
        txtNumero = (TextView) findViewById(R.id.txtNum);
        txtNumeroIntroduc = (TextView) findViewById(R.id.txtNumIntroduc);
        txtPuntos = (TextView) findViewById(R.id.txtPuntos);
        btnVerificar = (Button) findViewById(R.id.btnVerificar);
        btnComenzar = (Button) findViewById(R.id.btnComenzar);
        imgCoin = (ImageView) findViewById(R.id.imgCoin);
        imgPerro = (ImageView) findViewById(R.id.imgPerro);
        imgGato = (ImageView) findViewById(R.id.imgGato);
        txtNivel = (TextView)findViewById(R.id.txtNivel);
        txtVidas = (TextView) findViewById(R.id.txtVidas);
        layout = (ConstraintLayout) findViewById(R.id.layout);
        //Al iniciar no sera visible, se debera precionar en comenzar
        txtNumero.setVisibility(View.INVISIBLE);

        //.----------------
        mover();tambalear();
        //inciializara la musica
        musicaInit();
        //ninciar anim
        animarCoin();
        //Atrubutps
        puntos=0;
        nivel=1;
        vidas=3;
        sFondo.start();
        btnComenzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNumero.setVisibility(View.VISIBLE);
                sComenzar.start();
                puntos=0;
                nivel=1;
                vidas=3;
                txtPuntos.setText("0");
                txtNivel.setText("1");
                txtVidas.setText("3");
                sFondo.start();
              layout.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                numRandom();

            }
        });
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int numeroIntroduc = Integer.parseInt(txtNumeroIntroduc.getText().toString());


                    if (numeroIntroduc == Integer.parseInt(txtNumero.getText().toString())) {
                        puntos++;
                        txtPuntos.setText("" + puntos);
                        txtNumeroIntroduc.setText("");
                        numRandom();
                        sCoin.start();
                        sGood.start();



                    } else {

                        puntos--;
                        vidas--;
                        txtPuntos.setText("" + puntos);
                        txtVidas.setText("" + vidas);
                        txtNumeroIntroduc.setText("");

                        if (Integer.parseInt(txtPuntos.getText().toString()) < 0) {
                            Sbuu.start();
                            txtPuntos.setText("0");
                        } else {
                            Sbuu.start();

                        }
                        if (Integer.parseInt(txtVidas.getText().toString()) == 0) {
                            sFondo.stop();
                            Sbuu.stop();
                            sWo.stop();
                            sGood.stop();
                            Intent ventana = new Intent(getApplicationContext(),Main3Activity.class);
                            startActivity(ventana);
                        }

                    }
                }

        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sFondo.start();
    }

    public void numRandom() {
        nivel= Integer.parseInt(txtNivel.getText().toString());
        puntos= Integer.parseInt(txtPuntos.getText().toString());

        int numero=0;
        if(puntos==5){nivel++;sWo.start();
            layout.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
        };
        if(puntos==10){nivel++;sWo.start();
            layout.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));

        };
        if(puntos==15){nivel++;sWo.start();
            layout.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

        };
        if(puntos==20){nivel++;

            Intent ventana = new Intent(getApplicationContext(),Main5Activity.class);
            startActivity(ventana);
        };



        switch (nivel) {
            case 1:
                numero = (int) (Math.random() * 10) + 1;
                txtNumero.setText("" + numero);
                txtNivel.setText("1");
                break;
            case 2:
                numero = (int) (Math.random() * 100) + 1;
                txtNumero.setText("" + numero);
                txtNivel.setText("2");
                break;
            case 3:
                numero = (int) (Math.random() * 1000) + 1;
                txtNumero.setText("" + numero);
                txtNivel.setText("3");
                break;
            case 4:
                numero = (int) (Math.random() * 10000) + 1;
                txtNumero.setText("" + numero);
                txtNivel.setText("4");
                break;
        }

    }
//-----------------------------------------------ANIMACIONES----------------------------------
    public void animarCoin(){
        AnimationDrawable animarCoinn = (AnimationDrawable) ContextCompat.getDrawable(this,R.drawable.animacion_monedas);
        imgCoin.setImageDrawable(animarCoinn);
        animarCoinn.start();
    }






    public void  saltosAnim(){
        AnimationDrawable animacionSGato = (AnimationDrawable) ContextCompat.getDrawable(this,R.drawable.animationgsalto);
        AnimationDrawable animacionSperro= (AnimationDrawable) ContextCompat.getDrawable(this,R.drawable.animationperrosalto);
        imgGato.setImageDrawable(animacionSGato);
        imgPerro.setImageDrawable(animacionSperro);
        animacionSperro.start();
        animacionSGato.start();
    }

    public void mover(){

        Animation mover = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.traslacion);
        btnVerificar.startAnimation(mover);
    }
    public void tambalear(){
        Animation rotarDerecha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotacions);
        btnComenzar.startAnimation(rotarDerecha);

    }

    public void musicaInit() {
        sGood = MediaPlayer.create(getApplicationContext(), R.raw.good);
        sGood.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sComenzar = MediaPlayer.create(getApplicationContext(), R.raw.comenzar);
        sComenzar.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sFondo = MediaPlayer.create(getApplicationContext(), R.raw.musc);
        sFondo.setAudioStreamType(AudioManager.STREAM_MUSIC);
        sFondo.setLooping(true);

        Sbuu= MediaPlayer.create(getApplicationContext(), R.raw.buu);
        Sbuu.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sCoin = MediaPlayer.create(getApplicationContext(),R.raw.coin);
        sCoin.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sWo = MediaPlayer.create(getApplicationContext(),R.raw.wo);
        sWo.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sMusic = MediaPlayer.create(getApplicationContext(), R.raw.fin);
        sMusic.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }


    @Override
    protected void onPause() {
        super.onPause();
        sFondo.stop();
        Sbuu.stop();
        sCoin.stop();
        sWo.stop();
        sGood.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sFondo.release();
        sFondo.release();
        sCoin.release();
        sWo.release();
        sGood.release();

    }


    @Override
    protected void onStart() {
        super.onStart();
        sFondo.start();
    }
}
