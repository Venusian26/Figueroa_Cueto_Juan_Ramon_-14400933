package com.example.ricar.gestordepacientes;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class ListaPacientes extends AppCompatActivity {


    private ListView list;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    DatabaseHelper myDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_pacientes);

        list= (ListView) findViewById(R.id.lista);
        arrayList= new ArrayList<String>();
        adapter= new ArrayAdapter<String>(getApplicationContext(),R.layout.custom, arrayList);
        list.setAdapter(adapter);

        myDb = new DatabaseHelper(this);


        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, final long id) {
                // TODO Auto-generated method stub

                String[] opc = new String[]{"Editar", "Eliminar"};


                AlertDialog opciones = new AlertDialog.Builder(ListaPacientes.this).setTitle("Opciones").setItems(opc, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int selected) {
                        if (selected == 1) {


                            Cursor c = myDb.getAllDataCliente();
                            c.moveToPosition(pos);
                            int _id = c.getInt(0);
                            myDb.deleteDataCliente(c.getString(0));
                            arrayList.remove(pos);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();


                        } else if (selected == 0) {

                            Intent a = new Intent(ListaPacientes.this,ActualizarCliente.class);
                            Cursor d = myDb.getAllDataCliente();
                            d.moveToPosition(pos);
                            int _id = d.getInt(0);
                            String _nombre = d.getString(1);
                            String _direccion = d.getString(2);
                            String _cel = d.getString(3);
                            String _mail = d.getString(4);
                            String _fecha = d.getString(5);


                            a.putExtra("id", _id);
                            a.putExtra("nombre", _nombre);
                            a.putExtra("direccion", _direccion);
                            a.putExtra("celular", _cel);
                            a.putExtra("mail", _mail);
                            a.putExtra("fecha", _fecha);

                            startActivity(a);

                        }
                    }
                }).create();
                opciones.show();
                return true;
            }
        });



        Cursor c = myDb.getAllDataCliente();
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
        arrayList.add("Nombre: "+ c.getString(1) + "\n" + "Direccion: " + c.getString(2) + "\n " + "Celular: " + c.getString(3)+ "\n "
                +"Mail: "+c.getString(4)+ "\n "+"Fecha: "+c.getString(5));
        adapter.notifyDataSetChanged();


    }

    }



