package com.pedidos.sensores;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class Acelerometro extends Activity implements SensorEventListener {
    SensorManager sensorManager;
    int contador = 0;
    double x = 0, y = 0, z = 0, a = 0, amax = 0;
    double gravedad = SensorManager.STANDARD_GRAVITY;
    TextView tvax, tvay, tvaz, tva, tvaMax, tvG;

    // Called when the activity is first created.
    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acelerometro);

        tvax = (TextView) findViewById(R.id.textViewAX);
        tvay = (TextView) findViewById(R.id.textViewAY);
        tvaz = (TextView) findViewById(R.id.textViewAZ);
        tva = (TextView) findViewById(R.id.textViewA);
        tvaMax = (TextView) findViewById(R.id.textViewAmax);
        tvG = (TextView) findViewById(R.id.textViewG);

        // inicia un SensorManager
        SensorManager sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);

        // define un sensor acelerómetro
        Sensor acelerometro = sensorManager.getDefaultSensor(
                Sensor.TYPE_ACCELEROMETER);

        // registra el sensor para que comience a escuchar
        sensorManager.registerListener(
                this,
                acelerometro,
                SensorManager.SENSOR_DELAY_FASTEST);

        new MiAsyncTask().execute();
    }

    @Override
    public void onAccuracyChanged(Sensor argO, int argl) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // componentes de la aceleración
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        // modulo de la aceleración
        a = Math.sqrt(x * x + y * y + z * z);
        // aceleración máxima
        if (a > amax) amax = a;
    }

    class MiAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... argO) {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++;
                publishProgress();
            }
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            tvax.setText("" + x);
            tvay.setText("" + y);
            tvaz.setText("" + z);
            tva.setText("" + a);
            tvaMax.setText("" + amax);
            tvG.setText("" + gravedad);
            tvG.append("\n" + contador);
        }
    }
}