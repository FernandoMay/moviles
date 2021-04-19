package com.pedidos.sensores;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class OrientacionSensor extends Activity
        implements SensorEventListener {
    SensorManager sensorManager;
    Sensor sensor;
    int contador = 0;
    double azimut = 0, vertical = 0, lateral = 0;
    TextView tvAzimut, tvVertical, tvLateral,
            tvOrientacion, tvContador;
    String orientacion = "orientacion";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orientation);
        tvAzimut = (TextView)
                findViewById(R.id.textViewAzimut);
        tvVertical = (TextView)
                findViewById(R.id.textViewVertical);
        tvLateral = (TextView)
                findViewById(R.id.textViewLateral);
        tvOrientacion = (TextView)
                findViewById(R.id.textViewOrientación);
        tvContador = (TextView)
                findViewById(R.id.textViewContador);
// inicia un SensorManager
        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
// define un sensor de orientación
        sensor = sensorManager.getDefaultSensor(
                Sensor.TYPE_ORIENTATION);
    }

    @Override
    public void onResume() {
        super.onResume();
// inicia el sensor
        sensorManager.registerListener(
                this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        super.onPause();
// detiene el sensor
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
// ángulos de orientación
        azimut = event.values[0];
        vertical = event.values[1];
        lateral = event.values[2];
        contador++;

        if (azimut < 22) orientacion = "NORTE";
        else if (azimut < 67) orientacion = "NORESTE";
        else if (azimut < 112) orientacion = "ESTE";
        else if (azimut < 157) orientacion = "SURESTE";
        else if (azimut < 202) orientacion = "SUR";
        else if (azimut < 247) orientacion = "SUROESTE";
        else if (azimut < 292) orientacion = "OESTE";
        else if (azimut < 337) orientacion = "NOROESTE";
        else orientacion = "NORTE";
        if (vertical < -50) orientacion = "VERTICAL ARRIBA";
        if (vertical > 50) orientacion = "VERTICAL ABAJO";
        if (lateral > 50) orientacion = "LATERAL IZQUIERDA";
        if (lateral < -50) orientacion = "LATERAL DERECHA";
        runOnUiThread(new CambiaTexto());
    }

    class CambiaTexto implements Runnable {
        @Override
        public void run() {
// TODO Auto-generated method stub
            tvAzimut.setText("" + azimut);
            tvVertical.setText("" + vertical);
            tvLateral.setText("" + lateral);
            tvOrientacion.setText("" + orientacion);
            tvContador.setText("" + contador);
        } // end CambiaTexto
    }
}

