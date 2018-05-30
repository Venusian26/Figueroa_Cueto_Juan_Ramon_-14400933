package com.example.ricar.gestordepacientes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ricar on 28/09/2017.
 */

public class Consultar extends AppCompatActivity {

    Button consultar;

    DatabaseHelper myDb;
    EditText consultarid;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultar_layout);
        myDb = new DatabaseHelper(this);

        consultar = (Button) findViewById(R.id.consul_button);
        consultarid = (EditText) findViewById(R.id.consultar_clave);


        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int a = Integer.parseInt(consultarid.getText().toString());
                Cursor res = myDb.getMedicamentoDisponible(a);
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("*"+ res.getString(0));

                }

                // Show all data
                showMessage("Medicamentos del Paciente",buffer.toString());
            }

        });



    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}
