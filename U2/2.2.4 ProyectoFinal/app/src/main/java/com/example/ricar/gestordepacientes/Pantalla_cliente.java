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
 * Created by Ricar on 26/09/2017.
 */
public class Pantalla_cliente extends AppCompatActivity{


    DatabaseHelper myDb;
    EditText nombre;
    EditText direccion;
    EditText mail;
    EditText cel;
    EditText fecha;

    Button añadir;
    Button ver;
    Button verlista;
    Button pac_activos;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cliente_pantalla);
        myDb = new DatabaseHelper(this);

        nombre = (EditText) findViewById(R.id.nombre_edit);
        direccion = (EditText) findViewById(R.id.direccion_edit);
        mail = (EditText) findViewById(R.id.mail_edit);
        cel = (EditText) findViewById(R.id.celular_edit);
        fecha = (EditText) findViewById(R.id.fecha_edit);
        añadir = (Button) findViewById(R.id.añadir_boton);
        ver = (Button) findViewById(R.id.ver_boton);
        verlista = (Button) findViewById(R.id.ver_lista);
        pac_activos = (Button) findViewById(R.id.activos_boton);


        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertDataCliente(nombre.getText().toString(),
                        direccion.getText().toString(),cel.getText().toString(),
                        mail.getText().toString(),fecha.getText().toString()
                        );
                if(isInserted == true)
                    Toast.makeText(Pantalla_cliente.this,"Paciente añadido",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Pantalla_cliente.this,"Paciente NO añadido",Toast.LENGTH_LONG).show();

            }
        });


        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllDataCliente();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("NOMBRE :"+ res.getString(1)+"\n");
                    buffer.append("DIRECICON :"+ res.getString(2)+"\n");
                    buffer.append("CELULAR :"+ res.getString(3)+"\n");
                    buffer.append("MAIL :"+ res.getString(4)+"\n");
                    buffer.append("FECHA :"+ res.getString(5)+"\n");
                }

                // Show all data
                showMessage("Data",buffer.toString());
            }

        });

       verlista.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent a = new Intent(Pantalla_cliente.this,ListaPacientes.class);
               startActivity(a);
           }
       });



        pac_activos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s = new Intent(Pantalla_cliente.this,PacientesActivos.class);
                startActivity(s);
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



