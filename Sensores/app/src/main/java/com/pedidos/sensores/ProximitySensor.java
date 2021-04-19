package com.pedidos.sensores;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class ProximitySensor extends Activity
        implements SensorEventListener {
    SensorManager sensorManager;
    Sensor sensorLuz, sensorProximo;
    int contador = 0;
    double luz = 0, distancia = 0;
    TextView tvLuz, tvLuminosidad, tvProximidad, tvDistancia, tvContador;
    String luminosidad = "N O R M A L", proximidad = "LEJOS";

    /**
     * Called when the activity is first created.
     **/
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.proxlum);
        tvProximidad = (TextView) findViewById(R.id.textViewProximidad);
        tvDistancia = (TextView) findViewById(R.id.textViewDistancia);
        tvLuz = (TextView) findViewById(R.id.textViewLuz);
        tvLuminosidad = (TextView) findViewById(R.id.textViewLuminosidad);
        tvContador = (TextView) findViewById(R.id.textViewContador);
// inicia un SensorManager
        sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
// define sensores de proximidad y de luminosidad
        sensorProximo = sensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        sensorLuz = sensorManager.getDefaultSensor(
                Sensor.TYPE_LIGHT);
    }

    @Override
    public void onResume() {
        super.onResume();
// inicia el sensor
        sensorManager.registerListener(this, sensorLuz,
                SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, sensorProximo,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    public void onPause() {
        super.onPause();
// detiene el sensor
        sensorManager.unregisterListener(this);
    }

    @Override

    public void onAccuracyChanged(Sensor argO, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY)
            distancia = event.values[0];
        if (event.sensor.getType() == Sensor.TYPE_LIGHT)
            luz = event.values[0];
        contador++;
        if (distancia < 1) proximidad = "CERCA";
        else proximidad = "LEJOS";
        if (luz < 100) luminosidad = "OSCURO";
        else if (luz < 2000) luminosidad = "LUZ NORMAL";
        else if (luz < 6000) luminosidad = "BRILLANTE";
        else luminosidad = "MUCHA LUZ";
        runOnUiThread(new CambiaTexto());
    }

    class CambiaTexto implements Runnable {
        @Override
        public void run() {
// TODO Auto-generated method stub
            tvDistancia.setText("" + distancia);
            tvProximidad.setText("" + proximidad);
            tvLuz.setText("" + luz);
            tvLuminosidad.setText(" fl + luminosidad");
            tvContador.setText("" + contador);
        }
    } // end cambiaTexto
}