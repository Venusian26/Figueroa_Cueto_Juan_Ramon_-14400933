package com.example.ricar.gestordepacientes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    Button add;
    Button addmed;
    Button consultar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button) findViewById(R.id.add_cliente);
        addmed = (Button) findViewById(R.id.add_medicamento);
        consultar = (Button) findViewById(R.id.consultar_boton);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,Pantalla_cliente.class);
                startActivity(a);
            }
        });

        addmed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,Pantalla_medicamento.class);
                startActivity(a);
            }
        });

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this,Consultar.class);
                startActivity(a);
            }
        });


    }
}
