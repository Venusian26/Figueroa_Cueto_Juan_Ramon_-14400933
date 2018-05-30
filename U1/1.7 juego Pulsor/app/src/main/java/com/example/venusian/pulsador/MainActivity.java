package com.example.venusian.pulsador;

import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public TextView txtNum,txtPuntos;
    public Button btnPulsar,btnGo,btnNuevo;

    public double rdm = 1;
    public double rdmn;
    public Handler handler = new Handler();
    public int nivel=0;
    DecimalFormat decf = new DecimalFormat("#.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNum = (TextView) findViewById(R.id.txtNum);

        txtPuntos=(TextView)findViewById(R.id.txtPuntos);
        btnGo= (Button) findViewById(R.id.btnGo);
        btnPulsar = (Button) findViewById(R.id.btnGo);
        btnNuevo=(Button)findViewById(R.id.btnNuevo);

        rdmn = (Math.random()*(3.0-1.0))+1.0;




        btnPulsar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnPulsar.getText().equals("JUGAR")) {
                    btnGo.setText(decf.format(rdmn));
                    handler.postDelayed(runnable, 0);
                }else {
                    pulso();
                }
            }
        });
    btnNuevo.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                btnPulsar.setText("JUGAR");
             txtPuntos.setText("0");
            btnGo.setTextColor(Color.WHITE);
            handler.removeCallbacksAndMessages(null);
            txtNum.setText("0");

            }
        });


    }

    public void pulso(){
        double numero1 = Double.parseDouble(txtNum.getText().toString());
        double numero2 = Double.parseDouble(btnGo.getText().toString());
        int puntos=0;
        if(numero1 == numero2){
            rdmn = (Math.random()*(3.0-0.9))+1.0;
            btnGo.setText(decf.format(rdmn));
            btnGo.setTextColor(Color.GREEN);

            if(Integer.parseInt(txtPuntos.getText().toString())>=0) {
                puntos+=Integer.parseInt(txtPuntos.getText().toString())+1;
                txtPuntos.setText("" +puntos);
                txtNum.setText("0");


            }
        }

     else if(numero1 != numero2){
            Toast.makeText(MainActivity.this,"Lo Siento, Fallaste!", Toast.LENGTH_SHORT).show();
            rdmn = (Math.random()*(3.0-1.0))+1.0;
            btnGo.setText(decf.format(rdmn));
            btnGo.setTextColor(Color.RED);

          /* nivel=Integer.parseInt(txtPuntos.getText().toString());
           nivel=(nivel*10)*-1;
           handler.postDelayed(runnable,nivel);*/
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            reset();
            handler.postDelayed(this, 200);

        }

    };

    public void reset(){
        DecimalFormat df = new DecimalFormat("#.0");
        txtNum.setText(df.format(rdm));
        double temp = Double.parseDouble(txtNum.getText().toString());
        if(temp == 3.0){
            rdm = 1;
        }
        rdm = rdm+.1;
    }
}
