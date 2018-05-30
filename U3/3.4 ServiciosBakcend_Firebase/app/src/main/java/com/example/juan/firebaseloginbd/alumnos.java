package com.example.juan.firebaseloginbd;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class alumnos extends AppCompatActivity {
    private Button btnGuardar,btnModificar;
    private EditText txtNombre,txtNum;
    private DatabaseReference mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        //Relacion de componentes
        btnGuardar=(Button)findViewById(R.id.btnGuardar);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtNum=(EditText)findViewById(R.id.txtNum);

        //instancia de la referencia
        mydb =  FirebaseDatabase.getInstance().getReference();



        btnGuardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String valor=txtNombre.getText().toString();
            String valor2=txtNum.getText().toString();


            //obtener el id del elemento
            String id = mydb.push().getKey();

            //instanciamos la clase ALumnos
            Alumno alumno = new Alumno(valor,valor2);
            mydb.child("alumno").child(id).setValue(alumno);

            Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
        }
    });
    }
}
