package com.example.juan.webservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//Encender servicio de APache y PhpMyadmin
//Poner carpeta Datos1 en htdocs
//conectar el celular y la computadora a la misma red
//checar ip de la computadora otorgada por la red y cambiarla en cada direccion
public class MainActivity extends AppCompatActivity {
    int m=0;
    ListView lista;
    AdaptadorItem adaptadorI;
    segundoHilo hiloSegundo;
    String link="/datos1/obtener_alumnos.php";
    String json_string;
    String nombre1="",dir="",id="";
    String nombre[],direccion[];
    String ids[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        link="http://172.20.2.214:80"+link;

        lista=(ListView) findViewById(R.id.lista);
        //Intancionamos un nuevo objeto de hilo
        hiloSegundo = new segundoHilo();

        //pasamos de parametro el link y el estatus
        hiloSegundo.execute(link,"1");


        //relacionamos el boton
        ImageButton btnMas = (ImageButton) findViewById(R.id.btnMas);

        final Intent nv = new Intent(this,verDetalle.class);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                nv.putExtra("id", Integer.parseInt(ids[i]));
                startActivity(nv);
            }
        });
        //Si se presiona el boton de nuevo agregara un nuevo  e
        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(),insertar.class);
                startActivity(in);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
          int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.salir){
            Toast.makeText(getApplicationContext(),"La aplicacion se cerrara",Toast.LENGTH_SHORT);
            System.exit(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class segundoHilo extends AsyncTask<String, Void, String>{
        URL url;
        @Override
        protected String doInBackground(String... strings) {
            String cadena = "";
            if (strings[1]== "1"){
                try {
                    url = new URL(link);
                    HttpURLConnection connection = null; // Abrir conexion
                    connection = (HttpURLConnection) url.openConnection();


                    //Verificamos la conexion
                    int respuesta = 0;
                    respuesta = connection.getResponseCode();
                    InputStream inputStream = null;
                    inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();

                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        while ((json_string = bufferedReader.readLine()) != null) {
                            stringBuilder.append(json_string + "\n");
                        }
                        bufferedReader.close();
                        inputStream.close();
                        connection.disconnect();
                        String temporal = stringBuilder.toString();
                        JSONObject jsonObj = new JSONObject(temporal);

                        m=jsonObj.getInt("estado");

                        JSONArray arr=jsonObj.getJSONArray("alumnos");
                        for (int k=0;k<arr.length();k++){
                            JSONObject temp=arr.getJSONObject(k);
                            id+=temp.getString("idalumno")+"/";
                            nombre1+=temp.getString("nombre")+"/";
                            dir+=temp.getString("direccion")+"/";
                        }
                        cadena=id+"\n"+nombre1+"\n"+dir;
                        //cadena=id+nom+dir;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return  cadena;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            if(s.length()>0){
                String temp[]=s.split("\n");
                //Toast.makeText(getApplicationContext(),temp[2],Toast.LENGTH_SHORT).show();
                ids=temp[0].split("/");
                nombre=temp[1].split("/");
                direccion=temp[2].split("/");
                adaptadorI = new AdaptadorItem(getApplicationContext(),nombre,direccion);
                lista.setAdapter(adaptadorI);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


    }
}
