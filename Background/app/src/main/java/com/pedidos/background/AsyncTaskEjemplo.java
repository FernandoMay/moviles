package com.pedidos.background;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class AsyncTaskEjemplo extends Activity {
    TextView tv;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textview);
        new MiAsyncTask().execute(100);
    }

    class MiAsyncTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... parameter) {
            int maximo = parameter[0];
            for (int i = 1; i < maximo; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return "Fin";
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            int contador = progress[0];
            String texto = "Contador=" + contador;
            tv.setText(texto);
            tv.setTextSize(contador);
        }

        @Override
        protected void onPostExecute(String result) {
            tv.append("\n" + result);
        }
    }
}
