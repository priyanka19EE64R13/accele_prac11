package com.example.accele_prac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    private Sensor accelerometer, mGyro, mMagno, Light, Pressure, Temp;


    TextView xValue, yValue, zValue, xGyroValue, yGyroValue, zGyroValue, xMagnoValue, yMagnoValue, zMagnoValue, light, pressure, temp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);

        xGyroValue = (TextView) findViewById(R.id.xGyroValue);
        yGyroValue = (TextView) findViewById(R.id.yGyroValue);
        zGyroValue = (TextView) findViewById(R.id.zGyroValue);

        xMagnoValue = (TextView) findViewById(R.id.xMagnoValue);
        yMagnoValue = (TextView) findViewById(R.id.yMagnoValue);
        zMagnoValue = (TextView) findViewById(R.id.zMagnoValue);

        light = (TextView) findViewById(R.id.light);
        pressure = (TextView) findViewById(R.id.pressure);
        temp = (TextView) findViewById(R.id.temp);


        Log.d(TAG, "onCreate: Initialing Sensor services");
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (accelerometer != null) {
            sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered accelerometer Listener");
        } else {
            xValue.setText("Accelerometer not supported");
            yValue.setText("Accelerometer not supported");
            zValue.setText("Accelerometer not supported");
        }

        mGyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        if (mGyro != null) {
            sensorManager.registerListener(MainActivity.this, mGyro, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyro Listener");
        } else {
            xGyroValue.setText("Gyroscope not supported");
            yGyroValue.setText("Gyroscope not supported");
            zGyroValue.setText("Gyroscope not supported");
        }
        mMagno = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (mMagno != null) {
            sensorManager.registerListener(MainActivity.this, mMagno, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope Listener");
        } else {
            xMagnoValue.setText("Magno not supported");
            yMagnoValue.setText("Magno not supported");
            zMagnoValue.setText("Magno not supported");
        }


        Light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (Light != null) {
            sensorManager.registerListener(MainActivity.this, Light, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope Listener");
        } else {
            light.setText("Magno not supported");
        }

        Temp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (Temp != null) {
            sensorManager.registerListener(MainActivity.this, Temp, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope Listener");
        } else {
            temp.setText("temp not supported");
        }

        Pressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (Pressure != null) {
            sensorManager.registerListener(MainActivity.this, Pressure, SensorManager.SENSOR_DELAY_NORMAL);
            Log.d(TAG, "onCreate: Registered Gyroscope Listener");
        } else {
            pressure.setText("Pressure not supported");
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;

        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            Log.d(TAG, "onSensorChanged: X:" + sensorEvent.values[0] + "Y: " + sensorEvent.values[1] + "Z: " + sensorEvent.values[2]);

            xValue.setText("xValue :" + sensorEvent.values[0]);
            yValue.setText("yValue :" + sensorEvent.values[1]);
            zValue.setText("zValue :" + sensorEvent.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            xGyroValue.setText("xGValue :" + sensorEvent.values[0]);
            yGyroValue.setText("yGValue :" + sensorEvent.values[1]);
            zGyroValue.setText("zGValue :" + sensorEvent.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            xMagnoValue.setText("xMValue :" + sensorEvent.values[0]);
            yMagnoValue.setText("yMValue :" + sensorEvent.values[1]);
            zMagnoValue.setText("zMValue :" + sensorEvent.values[2]);
        } else if (sensor.getType() == Sensor.TYPE_LIGHT) {
            light.setText("light :" + sensorEvent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            temp.setText("temp :" + sensorEvent.values[0]);
        } else if (sensor.getType() == Sensor.TYPE_PRESSURE) {
            pressure.setText("pressure :" + sensorEvent.values[0]);
        }
    }
}

