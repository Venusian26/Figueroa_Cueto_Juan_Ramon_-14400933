package com.example.venusian.tablasmultiplicara1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText txtNum=(EditText) findViewById(R.id.txtNum);
        final TextView txtResultado=(TextView)findViewById(R.id.txtResultado);
        Button btnCalcular=(Button)findViewById(R.id.btnCalcular);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            int Numero=Integer.parseInt(String.valueOf(txtNum.getText()));


            int resultado=0;
            String resulta="";
            for (int i=1;i<=10;i++){
                resultado=Numero*i;
                resulta+=Numero+"x"+i+"="+resultado+"\n";
                txtResultado.setText("\n"+resulta);
            }


            }//evento

        });


    }
}
