package com.example.dss22.sensores;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private SensorManager sensorManager;
    RecyclerView recyclerView;

    SensorAdapter adapter;
    ArrayList<Sensors> list;

    @Override protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        sensorManager = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        List<Sensor> lista = sensorManager.getSensorList(Sensor.TYPE_ALL);

        list = new ArrayList<>();

        for(int i=1; i<lista.size(); i++) {

            list.add(new Sensors("Sensor: " + lista.get(i).getName(),
                    "Fabricante: " + lista.get(i).getVendor(),
                    "Version: " + lista.get(i).getVersion(),
                    "Rango Max: " + lista.get(i).getMaximumRange(),
                    "MinDelay: " + lista.get(i).getMinDelay(),
                    "Power: " + lista.get(i).getPower()));
        }

        adapter = new SensorAdapter(list,this);
        recyclerView.setAdapter(adapter);


    }
}