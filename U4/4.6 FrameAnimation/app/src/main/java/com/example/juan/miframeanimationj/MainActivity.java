package com.example.juan.miframeanimationj;

import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button btnIniciar,btnDetener;
    boolean runing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnDetener = (Button) findViewById(R.id.btnDetener);

        runing=false;
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarAnim();

            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AnimationDrawable) imageView.getDrawable()).stop();
            }
        });
    }

    private void iniciarAnim() {
        if(!runing) {
            ((AnimationDrawable) imageView.getDrawable()).start();
            runing=true;
        }else{
            ((AnimationDrawable) imageView.getDrawable()).stop();
            runing=false;
        }
    }
}
