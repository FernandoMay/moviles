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

public class DialogMultipleChoiceItems extends Activity
        implements OnClickListener {
    TextView tv;
    CharSequence[] items = {"item 0", "item l", "item 2",
            "item 3", "item 4", "item 5"};
    boolean[] marcas = new boolean[items.length];

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
        MListener mlistener = new MListener();
        Dialog dialogo = null;
        if (id == 0) {
            Builder builder = new AlertDialog.Builder(this);
            dialogo = builder
                    .setIcon(R.mipmap.ic_launcher)
                    .setTitle("Seleccione una de las opciones")
                    .setMultiChoiceItems(items, marcas, mlistener)
                    .setPositiveButton("OK", listener)
                    .setNegativeButton("Cancelar", listener)
                    .create();
        }
        return dialogo;
    }

    class DListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                tv.setText("Ha marcado los items");
                for (int i = 0; i < marcas.length; i++) {
                    if (marcas[i]) tv.append("\n " + items[i]);
                }
            }
            if (which == DialogInterface.BUTTON_NEGATIVE)
                tv.setText("Ha cancelado la opción");
        }
    }
}

// end Dialoglnterface.OnClickListener
class MListener implements
        DialogInterface.OnMultiChoiceClickListener {
    @Override
    public void onClick(DialogInterface dialog,
                        int which, boolean marca) {
    }
} // end Dialoglnterface.OnMultiChoiceClickListener
