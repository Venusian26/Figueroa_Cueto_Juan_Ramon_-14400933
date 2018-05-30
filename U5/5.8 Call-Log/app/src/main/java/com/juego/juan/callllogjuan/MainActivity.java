package com.juego.juan.callllogjuan;

import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar;
    Button llamadas;
    TextView textView;
    private String log;
    //para usar firebase
    private DatabaseReference mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        llamadas = (Button) findViewById(R.id.llamadas);
        textView = (TextView) findViewById(R.id.texto);

        //instanciamos la referencia a la bd
        //instancia de la referencia
        mydb =  FirebaseDatabase.getInstance().getReference();


        llamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObtenerDatosLlamadas();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //Obtener id
                    String id = mydb.push().getKey();
                    log = textView.getText().toString();
                    mydb.child("Log llamadas").child(id).setValue(log);
                Toast.makeText(getApplicationContext(),"Log Respaldado",Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void ObtenerDatosLlamadas() {

        Uri uri;

        uri = Uri.parse("content://call_log/calls");

        String[] projeccion = new String[]{CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DURATION};
        Cursor c = getContentResolver().query(
                uri,
                projeccion,
                null,
                null,
                null);

        textView.setText("");

        while(c.moveToNext()){
            textView.append("Tipo: " + c.getString(0) + " Número: " + c.getString(1) + " Duración: " + c.getString(2) +"\n");
        }
        c.close();
    }




}
