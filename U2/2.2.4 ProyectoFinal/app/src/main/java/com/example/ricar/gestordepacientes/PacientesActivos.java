package com.example.ricar.gestordepacientes;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ricar on 28/09/2017.
 */

public class PacientesActivos extends AppCompatActivity {


    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    DatabaseHelper myDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pacientes_activos);

        list= (ListView) findViewById(R.id.lista_pacientes_activos);
        arrayList= new ArrayList<String>();
        adapter= new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_pacientes_activos, arrayList);
        list.setAdapter(adapter);

        myDb = new DatabaseHelper(this);

        Cursor c = myDb.getPacientesActivos();
        if (c.moveToFirst()) {
            do
            {
                DisplayContact(c);
            }
            while
                    (c.moveToNext());
        }


    }

    public void DisplayContact(Cursor c) {
        arrayList.add("Nombre: "+ c.getString(0) + "\n "+ "Clave-Afiliacion: "+ c.getString(1));
        adapter.notifyDataSetChanged();


    }
}
