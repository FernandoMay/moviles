package com.pedidos.dialogos;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DialogoDeProgreso extends Activity
        implements OnClickListener{
    TextView tv;
    ProgressDialog progressDialog;
    int progreso,id;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedlnstanceState) {
        super.onCreate(savedlnstanceState);
        setContentView(R.layout.progressdialog);
        tv=(TextView) findViewById(R .id.textView);
        Button botonl= (Button) findViewById(R .id.buttonl);
        botonl.setOnClickListener(this);
        Button boton2= (Button) findViewById(R.id.button2);
        boton2.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        id=0;
        if(v.getId()==R.id.buttonl)
        id=1;
        showDialog(id);
        new MyAsyncTask().execute();
    }

    @Override
    protected
    Dialog onCreateDialog(int id) {
        progressDialog = new ProgressDialog(this);
        if(id==1)
        progressDialog.setProgressStyle(
                ProgressDialog.STYLE_HORIZONTAL);
else
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setIcon(R.mipmap.ic_launcher);
        progressDialog.setTitle("Progreso...");
        return progressDialog;
    }
    class MyAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void...arg0){
            for(int i=0;i<100;i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                progreso=i+1;
                publishProgress();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void...progress){
            progressDialog.setProgress(progreso);
            if(progreso==100)removeDialog(id);
            if (progreso==100)progressDialog.hide() ;
        }
    }
}