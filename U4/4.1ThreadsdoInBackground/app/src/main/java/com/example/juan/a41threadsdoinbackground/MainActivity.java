package com.example.juan.a41threadsdoinbackground;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private Button btn1,btn2;
private ProgressBar progressBar,progressBar2;
private EditText txtTiempo,txtNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        progressBar2=(ProgressBar)findViewById(R.id.progressBar2);
        txtTiempo=(EditText)findViewById(R.id.txtTiempo);
        txtNum2=(EditText)findViewById(R.id.txtNum2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

    }//ONCREATE

    public void onClick(View vista){
        switch (vista.getId()){
            case R.id.btn1:
                new Asin2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                break;
            case R.id.btn2:
               new ejemploAsyn().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                //hilo();
                break;
        }
    }


    public void unSegundo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Toast.makeText(getApplicationContext(), "Hilo interurmpido", Toast.LENGTH_SHORT).show();
        }
    }//UN segundo
    public void hilo(){
        progressBar.setMax(100);

        int tiempo=Integer.parseInt(txtTiempo.getText().toString());
        for (int i=1; i<=tiempo; i++) {
            unSegundo();
            //multiplicara el progreso por 10
            progressBar.setProgress(i*100/tiempo);

        }

    }
    private class Asin2 extends AsyncTask<Void,Integer,Boolean>{

        //Se ejecuta primero en el hilo principal
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar2.setMax(100);
        }

        //Lo que hace en segundo plano, cuando termina se ejecuta la funcion onPostExecute
        @Override
        protected Boolean doInBackground(Void... params) {
            int tiempo=Integer.parseInt(txtNum2.getText().toString());
            for (int i=1; i<=tiempo; i++) {
                unSegundo();
                //multiplicara el progreso por 10
                progressBar2.setProgress(i*100/tiempo);
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }
        //Funcion que se ejecuta en el hilo principal de usuario
        //Cuando se llama a publish progress se ejecuta
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //Actualizar la barra de progreso
            progressBar2.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            //super.onPostExecute(resultado);
            if(resultado==true){

                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask",Toast.LENGTH_SHORT).show();
                ;
            }
        }


        //Cuando la operacion es cancelada
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(),"Tarea Cancelada",Toast.LENGTH_SHORT).show();

        }


    }




    private class ejemploAsyn extends AsyncTask<Void,Integer,Boolean>{

        //Se ejecuta primero en el hilo principal
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
        }

        //Lo que hace en segundo plano, cuando termina se ejecuta la funcion onPostExecute
        @Override
        protected Boolean doInBackground(Void... params) {
            int tiempo=Integer.parseInt(txtTiempo.getText().toString());
            for (int i=1; i<=tiempo; i++) {
                    unSegundo();
                    //multiplicara el progreso por 10
                    progressBar.setProgress(i*100/tiempo);
                if (isCancelled()){
                    break;
                }
            }
            return true;
        }
        //Funcion que se ejecuta en el hilo principal de usuario
        //Cuando se llama a publish progress se ejecuta
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //Actualizar la barra de progreso
            progressBar.setProgress(values[0].intValue());
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            //super.onPostExecute(resultado);
            if(resultado==true){

                Toast.makeText(getApplicationContext(),"Tarea finaliza AsyncTask",Toast.LENGTH_SHORT).show();
              ;
            }
        }


        //Cuando la operacion es cancelada
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(),"Tarea Cancelada",Toast.LENGTH_SHORT).show();

        }


    }

}
