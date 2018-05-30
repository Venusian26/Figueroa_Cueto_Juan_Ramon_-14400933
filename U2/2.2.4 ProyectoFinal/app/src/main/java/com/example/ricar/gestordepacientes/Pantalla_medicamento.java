package com.example.ricar.gestordepacientes;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Ricar on 27/09/2017.
 */

public class Pantalla_medicamento extends AppCompatActivity {

    EditText nombre;
    EditText padecimiento;
    EditText instrucciones;
    EditText fechaconsulta;
    EditText fechainicio;
    EditText fechafin;
    EditText vigencia;
    EditText paciente;

    Button añadirMed;
    Button verRecetas;
    Button botonlistarecetas;

    DatabaseHelper myDb;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicamento_pantalla);

        nombre = (EditText) findViewById(R.id.nombre_med);
        padecimiento = (EditText) findViewById(R.id.pade_med);
        instrucciones = (EditText) findViewById(R.id.inst_med);
        fechaconsulta = (EditText) findViewById(R.id.fechcon_med);
        fechainicio = (EditText) findViewById(R.id.fecin_med);
        fechafin = (EditText) findViewById(R.id.fecfin_med);
        vigencia = (EditText) findViewById(R.id.vigencia_med);
        paciente = (EditText) findViewById(R.id.paciente_med);


        añadirMed = (Button) findViewById(R.id.añadir_med);
        verRecetas = (Button) findViewById(R.id.ver_recetas);
        botonlistarecetas = (Button) findViewById(R.id.boton_lista_recetas);

        myDb = new DatabaseHelper(this);

      añadirMed.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              boolean isInserted = myDb.insertDataMed(nombre.getText().toString(),
                      padecimiento.getText().toString(),
                      instrucciones.getText().toString(),
                      fechaconsulta.getText().toString(),
                      fechainicio.getText().toString(),
                      fechafin.getText().toString(),
                      vigencia.getText().toString(),paciente.getText().toString()
                      );
              if(isInserted == true)
                  Toast.makeText(Pantalla_medicamento.this,"Medicamento Recetado",Toast.LENGTH_LONG).show();
              else
                  Toast.makeText(Pantalla_medicamento.this,"Medicamento NO recetado",Toast.LENGTH_LONG).show();
          }
      });

        verRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllDataMed();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("MEDICAMENTO :"+ res.getString(1)+"\n");
                    buffer.append("PADECIMIENTO :"+ res.getString(2)+"\n");
                    buffer.append("INSTRUCCIONES :"+ res.getString(3)+"\n");
                    buffer.append("FECHA CONSULTA:"+ res.getString(4)+"\n");
                    buffer.append("FECHA INICIO:"+ res.getString(5)+"\n");
                    buffer.append("FECHA FIN:"+ res.getString(6)+"\n");
                    buffer.append("VIGENCIA:"+ res.getString(7)+"\n");
                    buffer.append("PACIENTE:"+ res.getString(8)+"\n");
                }

                // Show all data
                showMessage("Data",buffer.toString());
            }
        });

        /////////Boton para intent de la lista

        botonlistarecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a = new Intent(Pantalla_medicamento.this,ListaRecetas.class);
                startActivity(a);
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

