package com.pedidos.dialogos;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DialogoConBotones extends Activity
        implements OnClickListener {
    TextView tv;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog2);
        tv = (TextView) findViewById(R.id.textView);
        Button boton = (Button) findViewById(R.id.buttonl);
        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showDialog(0);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        DListener listener = new DListener();
        Dialog dialogo = null;
        if (id == 0) {
            Builder builder = new AlertDialog.Builder(this);
            builder = builder.setIcon(R.mipmap.ic_launcher);
            builder = builder.setTitle(
                    "Esto es un diálogo con botones");
            builder = builder.setMessage("Este es el mensaje." +
                    "\n Pulse uno de los tres botones para continuar");
            builder = builder.setPositiveButton("Botón positivo", (DialogInterface.OnClickListener) listener);
            builder = builder.setNegativeButton("Botón negativo", (DialogInterface.OnClickListener) listener);
            builder = builder.setNeutralButton("Botón neutro", (DialogInterface.OnClickListener) listener);
            dialogo = builder.create();
        }
        return dialogo;
    }

    class DListener
            implements
            DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE)
                tv.setText("Botón positivo pulsado");
            if (which == DialogInterface.BUTTON_NEGATIVE)
                tv.setText("Botón negativo pulsado");
            if (which == DialogInterface.BUTTON_NEUTRAL)
                tv.setText("Botón neutro pulsado");
        }
    } // end Dialoglnterface.OnClickListener
}
