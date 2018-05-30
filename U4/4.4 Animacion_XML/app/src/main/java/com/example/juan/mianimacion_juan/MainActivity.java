package com.example.juan.mianimacion_juan;

import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private Button btnTrans,btnRotacion,btnEscalacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView)findViewById(R.id.imagen);
        btnTrans = (Button) findViewById(R.id.btnTrans);
        btnRotacion = (Button) findViewById(R.id.btnRot);
        btnEscalacion = (Button) findViewById(R.id.btnEsc);


        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {translacion(); }
        });

        btnRotacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnEscalacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escalacion();
            }
        });

    }

    private void rotacion() {
        //Rotacion
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotacion);
        img.startAnimation(animation);

    }

    public void translacion(){
        //Craemos
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.trans);
        img.startAnimation(animation);
    }


    public void escalacion(){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.escalacion);
        img.startAnimation(animation);

    }
}
