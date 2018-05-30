package com.example.jesus.a53giroscopio;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private Sensor mysensor;
    private SensorManager senman;
    private TextView ejex, ejey, ejez;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        senman = (SensorManager) getSystemService(SENSOR_SERVICE);
        mysensor=senman.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        ejex = (TextView) findViewById(R.id.ejex);
        ejey = (TextView) findViewById(R.id.ejey);
        ejez = (TextView) findViewById(R.id.ejez);

        senman.registerListener((SensorEventListener) this,mysensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        ejex.setText(""+sensorEvent.values[0]);
        ejey.setText(""+sensorEvent.values[1]);
        ejez.setText(""+sensorEvent.values[2]);


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
