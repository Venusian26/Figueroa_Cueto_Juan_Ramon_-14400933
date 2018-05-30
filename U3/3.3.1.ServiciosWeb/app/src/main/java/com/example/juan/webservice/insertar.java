package com.example.juan.webservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class insertar extends AppCompatActivity {
    String link="/datos1/insertar_alumno.php";

    EditText txNombre,txDireccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        link="http://172.20.2.214:80"+link;

        txNombre=(EditText)findViewById(R.id.nomIn);
        txDireccion=(EditText)findViewById(R.id.dirIn);

        findViewById(R.id.btnInsertar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodosPost mp=new metodosPost();

                if(!mp.insertar(link,txNombre.getText().toString(),txDireccion.getText().toString())){
                    Toast.makeText(getApplication(),"Fallo al guardar",Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(getApplication(),"Guardado con exito",Toast.LENGTH_SHORT).show();
                Intent nv = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(nv);
            }
        });
    }
}
