package com.example.ricar.gestordepacientes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActualizarCliente extends AppCompatActivity {


    DatabaseHelper myDb;
    EditText nombre;
    EditText direccion;
    EditText mail;
    EditText cel;
    EditText fecha;

    Button añadir;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_actualizar);

        myDb = new DatabaseHelper(this);

        nombre = (EditText) findViewById(R.id.nombre_actu);
        direccion = (EditText) findViewById(R.id.direccion_actu);
        mail = (EditText) findViewById(R.id.mail_actu);
        cel = (EditText) findViewById(R.id.celular_actu);
        fecha = (EditText) findViewById(R.id.fecha_actu);
        añadir = (Button) findViewById(R.id.actualizar_boton);


        Intent intent = getIntent();
        String tecy = intent.getStringExtra("nombre");
        String tecy2 = intent.getStringExtra("direccion");
        String tecy3 = intent.getStringExtra("celular");
        String tecy5 = intent.getStringExtra("mail");
        String tecy6 = intent.getStringExtra("fecha");


        final int tecy4 = intent.getIntExtra("id", 0);
        final String id_n = Integer.toString(tecy4);

        if (tecy != null && tecy2 != null && tecy3 != null) {
            nombre.setText(tecy);
            direccion.setText(tecy2);
            cel.setText(tecy3);
            mail.setText(tecy5);
            fecha.setText(tecy6);


        }


        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateDataCliente(id_n, nombre.getText().toString(), direccion.getText().toString(),
                        cel.getText().toString(), mail.getText().toString(),
                        fecha.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(ActualizarCliente.this, "Dato Actualizado", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ActualizarCliente.this, "Dato NO actualizado", Toast.LENGTH_LONG).show();
            }


        });


    }

}
