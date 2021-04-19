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
import android.widget.Toast;

public class CampoMagnetico extends Activity implements SensorEventListener {
    SensorManager sensorManager;
    int contador = 0;
    boolean continuar = true;
    double x = 0, y = 0, z = 0, a = 0, amax = 0;
    double campoTierraMax
            = SensorManager.MAGNETIC_FIELD_EARTH_MAX;
    double campoTierraMin
            = SensorManager.MAGNETIC_FIELD_EARTH_MIN;
    TextView tvax, tvay, tvaz, tva, tvaMax, tvG;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.magnetic);

        tvax = (TextView) findViewById(R.id.textViewBX);
        tvay = (TextView) findViewById(R.id.textViewBY);
        tvaz = (TextView) findViewById(R.id.textViewBZ);
        tva = (TextView) findViewById(R.id.textViewB);
        tvaMax = (TextView) findViewById(R.id.textViewBmax);
        tvG = (TextView) findViewById(R.id.textViewBTierra);

        // inicia un SensorManager
        SensorManager sensorManager = (SensorManager)
                getSystemService(Context.SENSOR_SERVICE);
        // define un sensor campo magnético
        Sensor campo = sensorManager.getDefaultSensor(
                Sensor.TYPE_MAGNETIC_FIELD);
        // registra el sensor para que comience a escuchar
        sensorManager.registerListener(
                this, campo, SensorManager.SENSOR_DELAY_NORMAL);
        new MiAsyncTask().execute();
    }

    @Override
    public void onResume() {
        super.onResume();
        continuar = true;
        new MiAsyncTask().execute();
    }

    @Override
    public void onPause() {
        super.onPause();
        continuar = false;
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // componentes del campo
        x = event.values[0];
        y = event.values[1];
        z = event.values[2];
        // modulo
        a = Math.sqrt(x * x + y * y + z * z);
        // máximo
        if (a > amax) amax = a;
    }

    class MiAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override

        protected Void doInBackground(Void... arg0) {
            while (continuar) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                contador++;
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            tvax.setText("" + x);
            tvay.setText("" + y);
            tvaz.setText("" + z);
            tva.setText("" + a);
            tvaMax.setText("" + amax);
            tvG.setText("" + campoTierraMin + " - " + campoTierraMax);
            tvG.append("\n" + contador);
        }

        @Override

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Context context = getApplicationContext();
            Toast.makeText(context,
                    "Campo Magnético AsyncTask Terminado", Toast.LENGTH_LONG).show();
        }
    }
}