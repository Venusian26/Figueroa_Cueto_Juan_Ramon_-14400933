package com.app.juan.sqlite1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText nombre,apellido;
    TextView vista;
    Button añadir,borrar,actualizar,mostrar;
    DB_Controller controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        vista = findViewById(R.id.txtVista);
        añadir = findViewById(R.id.btnAñadir);
        borrar = findViewById(R.id.btnBorrar);
        actualizar = findViewById(R.id.btnActualizar);
        mostrar = findViewById(R.id.btnMostrar);


        controller = new DB_Controller(this,"",null,1);



        añadir.setOnClickListener(new View.OnClickListener() {



            public void onClick (View v){

                controller.insertar_estudiante(nombre.getText().toString(), apellido.getText().toString());

            }



        });

        borrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                controller.borrar_estudiante(nombre.getText().toString());

            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            String txttemp;
            public void onClick(View v) {
                txttemp = apellido.getText().toString();
                controller.actualizar(apellido.getText().toString(),nombre.getText().toString());

            }
        });

        mostrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                controller.enlistar_estudiantes(vista);

            }
        });

    }
}
