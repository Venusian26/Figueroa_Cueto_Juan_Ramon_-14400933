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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



/**
 * Created by Ricar on 28/09/2017.
 */

public class ListaRecetas extends AppCompatActivity {

    private ListView listarecetas;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;
    DatabaseHelper myDb;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_recetas);


        listarecetas= (ListView) findViewById(R.id.lista_recetas_view);
        arrayList= new ArrayList<String>();
        adapter= new ArrayAdapter<String>(getApplicationContext(),R.layout.costum_recetas, arrayList);
        listarecetas.setAdapter(adapter);

        myDb = new DatabaseHelper(this);


        ///////al dar clic en el elemento de la lista

        listarecetas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, final long id) {
                // TODO Auto-generated method stub

                String[] opc = new String[]{"Editar", "Eliminar"};


                AlertDialog opciones = new AlertDialog.Builder(ListaRecetas.this).setTitle("Opciones").setItems(opc, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int selected) {
                        if (selected == 1) {


                            Cursor c = myDb.getAllDataMed();
                            c.moveToPosition(pos);
                            int _id = c.getInt(0);
                            myDb.deleteDataMed(c.getString(0));
                            arrayList.remove(pos);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(getApplicationContext(), "Eliminado", Toast.LENGTH_SHORT).show();


                        } else if (selected == 0) {

                            Intent a = new Intent(ListaRecetas.this,ActualizarReceta.class);
                            Cursor d = myDb.getAllDataMed();
                            d.moveToPosition(pos);
                            int _id = d.getInt(0);
                            String _medicamento = d.getString(1);
                            String _padex = d.getString(2);
                            String _instrucciones = d.getString(3);
                            String _fechaconsulta = d.getString(4);
                            String _fechainicio = d.getString(5);
                            String _fechafin = d.getString(6);
                            String _vigencia = d.getString(7);
                            String _paciente = d.getString(8);


                            a.putExtra("id", _id);
                            a.putExtra("medicamento", _medicamento);
                            a.putExtra("padex", _padex);
                            a.putExtra("instrucciones", _instrucciones);
                            a.putExtra("fechaconsulta", _fechaconsulta);
                            a.putExtra("fechainicio", _fechainicio);
                            a.putExtra("fechafin", _fechafin);
                            a.putExtra("vigencia", _vigencia);
                            a.putExtra("paciente", _paciente);

                            startActivity(a);

                        }
                    }
                }).create();
                opciones.show();
                return true;
            }
        });





        Cursor c = myDb.getAllDataMed();
        if (c.moveToFirst()) {
            do
            {
                DisplayContact(c);
            }
            while
                    (c.moveToNext());
        }


    }//onCreate

    public void DisplayContact(Cursor c) {
        arrayList.add("Medicamento: "+ c.getString(1) + "\n" + "Padecimiento: " + c.getString(2) + "\n "
                + "Instrucciones: " + c.getString(3)+ "\n "
                +"Fecha Consulta: "+c.getString(4) + "\n "
                +"Fecha Inicio: "+c.getString(5)+ "\n "
                +"Fecha Fin: "+c.getString(6)+ "\n "
                +"Vigencia: "+c.getString(7)+ "\n"+"Paciente: "+c.getString(8));
        adapter.notifyDataSetChanged();


    }


}//Class
