package com.example.ricar.gestordepacientes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ricar on 28/09/2017.
 */

public class ActualizarReceta extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText medicamento;
    EditText padecimiento;
    EditText instrucciones;
    EditText fechaconsulta;
    EditText fechainicio;
    EditText fechafin;
    EditText vigencia;
    EditText paciente;

    Button añadir;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_actualizar);

        myDb = new DatabaseHelper(this);

        medicamento = (EditText) findViewById(R.id.nombre_med_actu);
        padecimiento = (EditText) findViewById(R.id.pade_med_actu);
        instrucciones = (EditText) findViewById(R.id.inst_med_actu);
        fechaconsulta = (EditText) findViewById(R.id.fechcon_med_actu);
        fechainicio = (EditText) findViewById(R.id.fecin_med_actu);
        fechafin= (EditText) findViewById(R.id.fecfin_med_actu);
        vigencia = (EditText) findViewById(R.id.vigencia_med_actu);
        paciente = (EditText) findViewById(R.id.paciente_med_actu);


        añadir = (Button) findViewById(R.id.añadir_med_actu);


        Intent intent = getIntent();
        String tecy = intent.getStringExtra("medicamento");
        String tecy2 = intent.getStringExtra("padex");
        String tecy3 = intent.getStringExtra("instrucciones");
        String tecy5 = intent.getStringExtra("fechaconsulta");
        String tecy6 = intent.getStringExtra("fechainicio");
        String tecy7 = intent.getStringExtra("fechafin");
        String tecy8 = intent.getStringExtra("vigencia");
        String tecy9 = intent.getStringExtra("paciente");


        final int tecy4 = intent.getIntExtra("id", 0);
        final String id_n = Integer.toString(tecy4);

        if (tecy != null && tecy2 != null && tecy3 != null&& tecy3 != null &&
                tecy5 != null && tecy6 != null && tecy7 != null&& tecy8 != null && tecy9 != null) {
            medicamento.setText(tecy);
            padecimiento.setText(tecy2);
            instrucciones.setText(tecy3);
            fechaconsulta.setText(tecy5);
            fechainicio.setText(tecy6);
            fechafin.setText(tecy7);
            vigencia.setText(tecy8);
            paciente.setText(tecy9);

        }

        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateDataMed(id_n,medicamento.getText().toString(), padecimiento.getText().toString(),
                        instrucciones.getText().toString(), fechaconsulta.getText().toString(),
                        fechainicio.getText().toString(),fechafin.getText().toString(),vigencia.getText().toString(),
                        paciente.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(ActualizarReceta.this, "Receta Actualizada", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ActualizarReceta.this, "Receta NO actualizada", Toast.LENGTH_LONG).show();
            }


        });



    }

}
