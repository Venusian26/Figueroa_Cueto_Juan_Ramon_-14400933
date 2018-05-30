package com.example.juan.firebaseloginbd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    //declaramos una instancia de firebase
    private FirebaseAuth mAuth;

    //Componenest
    EditText txtCorreo,txtPass;
    Button btnLoguear,btnRegistrar;
    ProgressDialog bPogreso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos la instancia
        mAuth = FirebaseAuth.getInstance();
        //referenciamos los componentes con el activity
        txtCorreo = (EditText)findViewById(R.id.txtCorreo);
        txtPass = (EditText)findViewById(R.id.txtPass);
        btnLoguear = (Button)findViewById(R.id.btnLoguear);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
        //barra de progreso
        bPogreso = new ProgressDialog(this);

        //Evento del boton registrar
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            registrar();
            }
        });

        //Evento Loguear
        btnLoguear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loguear();

            }
        });



    }
    //metodo para loguear al usuario
    private void loguear() {
        String correo = txtCorreo.getText().toString();
        String pass = txtPass.getText().toString();
        if (!TextUtils.isEmpty(correo) && !TextUtils.isEmpty(pass)) {
            bPogreso.setMessage("Iniciando Sesion");
            bPogreso.show();

            mAuth.signInWithEmailAndPassword(correo, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            bPogreso.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Logueado con exito", Toast.LENGTH_SHORT).show();
                                //Si se loguea abriremos el nuevo activity donde se registran los alumnos

                                Intent alumnos = new Intent(getApplicationContext(),alumnos.class);
                                startActivity(alumnos);
                            } else {
                                Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(), "Ingrese sus datos", Toast.LENGTH_SHORT).show();

        }
    }
    private void registrar() {
        //ibtenemos el valor en lso campos
    String correo = txtCorreo.getText().toString();
    String pass = txtPass.getText().toString();

    //verificamos que no esten vacios los campos antes de realizar la accion
        if(!TextUtils.isEmpty(correo)&&!TextUtils.isEmpty(pass)){
            bPogreso.setMessage("Registrando, espera...");
            bPogreso.show();
            //creara el password y correo
            mAuth.createUserWithEmailAndPassword(correo,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        bPogreso.dismiss();
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Registrado con exito",Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
        }else{
            Toast.makeText(getApplicationContext(),"No dejes campos vacios",Toast.LENGTH_SHORT).show();

        }
    }

    //verifica si el usuario està actualmente conectado o no
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }


}
