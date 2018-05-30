package com.example.juan.laboratorio_asyntask;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn2;
    private ProgressBar progressBar;
    private EditText txtFactorial;
    private TextView txtResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn2=(Button)findViewById(R.id.btn2);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);

        txtFactorial=(EditText)findViewById(R.id.txtFactorial);
        txtResultado=(TextView)findViewById(R.id.txtResultado);
        btn2.setOnClickListener(this);

    }//ONCREATE

    public void onClick(View vista){
        switch (vista.getId()){

            case R.id.btn2:
                new ejemploAsyn().execute();
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


    private class ejemploAsyn extends AsyncTask<Void,Integer,Integer>{

        //Se ejecuta primero en el hilo principal
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setMax(100);
        }

        //Lo que hace en segundo plano, cuando termina se ejecuta la funcion onPostExecute
        @Override
        protected Integer doInBackground(Void... params) {
            int resultado = 1;

            int numeroFac=Integer.parseInt(txtFactorial.getText().toString());
            for (int i = 1; i <=numeroFac; i++) {
                resultado *= i;
                unSegundo();

                progressBar.setProgress(i*10);

                if (isCancelled()){
                    break;
                }
            }

            //multiplicara el progreso por 10


            return resultado;
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
        protected void onPostExecute(Integer resultado) {
            //super.onPostExecute(resultado);
            txtResultado.setText(resultado+"");

                Toast.makeText(getApplicationContext(),"Factorial Calculado",Toast.LENGTH_SHORT).show();
                   }


        //Cuando la operacion es cancelada
        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getApplicationContext(),"Tarea Cancelada",Toast.LENGTH_SHORT).show();

        }


    }

    private void mostrarResultado(int resultado) {
    txtResultado.setText(resultado);
    }

}
