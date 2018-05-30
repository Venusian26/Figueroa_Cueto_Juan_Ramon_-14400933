package com.juego.juan.juego;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    private ImageButton btnComenzar;
    private ImageView imgPerro,imgPato,btnInfo,imgPuerco,imgJirafa;
    private MediaPlayer mediaPlayer,sComenzar,sPato,sPuerco,sJirafa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnComenzar = (ImageButton) findViewById(R.id.btnComenzar);
        btnInfo = (ImageView) findViewById(R.id.btnInfo);
        //referencias
        imgPerro = (ImageView) findViewById(R.id.imgPerro);
        imgPato =   (ImageView) findViewById(R.id.imgPato);
        imgPuerco = (ImageView)findViewById(R.id.imgPuerco);

        imgJirafa = (ImageView)findViewById(R.id.imgjirafa);
        //cargara la musica
        musicaInit();

        //para la animacion del perro

        iniciarAnimP();

        //animacion tambelo boton
        mover();
        tambalear();
        mov();
    //Si se preiona o no
    btnComenzar.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if(event.getAction() == MotionEvent.ACTION_DOWN){
                //presionado
             btnComenzar.setBackgroundColor(getResources().getColor(R.color.amarillo));
                iniciarAnimP();
            sComenzar.start();


            }else if (event.getAction() == MotionEvent.ACTION_UP){
                //No presionado
                btnComenzar.setBackgroundColor(getResources().getColor(R.color.naranja));
                Intent ventana = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(ventana);
            }
            return true;
        }
    });
    btnInfo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //El acerca de:
            btnInfo.setBackgroundColor(getResources().getColor(R.color.naranja));
            Intent ventana = new Intent(getApplicationContext(),Main4Activity.class);
            startActivity(ventana);
        }
    });
    // si se toca la imagen del prro
    imgPerro.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Animation rotar = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotar);
            imgPerro.startAnimation(rotar);
        }


    });
    imgPato.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sPato.start();
        }
    });


    imgPuerco.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sPuerco.start();
        }
    });

    imgJirafa.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            sJirafa.start();
        }
    });
    }


//______________________ANIMACIONES_________________________________________
      //animacion del perro
    private void iniciarAnimP() {
        AnimationDrawable animacionPerro = (AnimationDrawable) ContextCompat.getDrawable(this,R.drawable.animationperro);
        imgPerro.setImageDrawable(animacionPerro);
        animacionPerro.start();
    }

    public void mover(){

        Animation mover = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.traslacion);
        btnComenzar.startAnimation(mover);
        btnInfo.startAnimation(mover);
    }
    public void tambalear(){
        Animation rotarDerecha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotacionbtnderecha);
        imgPato.startAnimation(rotarDerecha);

    }
    public void mov(){

        Animation mov = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.traslacin_imgs);
        imgPuerco.startAnimation(mov);
        imgJirafa.startAnimation(mov);
    }
    //_________________________________________________________________________________
    //Al iniciarse la apliacion se reproducira la musica
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    //al ponerse en segundo plano se detendra

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }

    //Cuando regrese denuevo

    @Override
    protected void onRestart() {
        super.onRestart();
        musicaInit();
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();

        }

    //Cuando iniciar el juego
    public void OnclickIni() {

    }
    public void musicaInit(){
        mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.loop);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);


        sComenzar = MediaPlayer.create(getApplicationContext(),R.raw.click);
        sComenzar.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sPato = MediaPlayer.create(getApplicationContext(),R.raw.cuack);
        sPato.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sPuerco = MediaPlayer.create(getApplicationContext(),R.raw.puerco);
        sPuerco.setAudioStreamType(AudioManager.STREAM_MUSIC);

        sJirafa = MediaPlayer.create(getApplicationContext(),R.raw.jirafa);
        sJirafa.setAudioStreamType(AudioManager.STREAM_MUSIC);


    }
}
