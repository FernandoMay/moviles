package com.pedidos.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Dialogo extends Activity
        implements OnClickListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog1);
        Button boton = (Button) findViewById(R.id.buttonl);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        showDialog(0);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialogo = null;
        if (id == 0) {
            Builder builder = new AlertDialog.Builder(this);
            builder = builder.setIcon(R.mipmap.ic_launcher);
            builder = builder.setTitle(
                    "Este es el aspecto de un diálogo");
            dialogo = builder.create();
        }
        return dialogo;
    }
}