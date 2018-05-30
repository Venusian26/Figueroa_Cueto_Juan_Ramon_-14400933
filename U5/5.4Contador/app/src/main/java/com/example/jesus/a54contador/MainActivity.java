package com.example.jesus.a54contador;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener, StepListener {

    private TextView textView;
    private RelativeLayout fondo;
    private SimpleStepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private TextView texto;
    private static final String TEXT_NUM_STEPS = " ";
    private int numSteps;

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto= (TextView) findViewById(R.id.textView);

        fondo = new RelativeLayout(this);
        fondo.setBackgroundResource(R.drawable.pasos);


        textView = new TextView(this);
        textView.setTextSize(60);
        textView.setHeight(20);
        textView.setPadding(300,500,0,0);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numSteps=0;
                texto.setText("0");

            }
        });


        //setContentView(textView);
        //setContentView(fondo);



        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new SimpleStepDetector();
        simpleStepDetector.registerListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        numSteps = 0;
        texto.setText(TEXT_NUM_STEPS + numSteps);
        sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    sensorEvent.timestamp, sensorEvent.values[0], sensorEvent.values[1], sensorEvent.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void step(long timeNs) {
        numSteps++;
        texto.setText(TEXT_NUM_STEPS + numSteps);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
