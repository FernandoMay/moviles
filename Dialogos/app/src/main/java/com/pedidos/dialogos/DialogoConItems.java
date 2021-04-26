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

public class DialogoConItems extends Activity implements OnClickListener {
    TextView tv;
    CharSequence[] items =
            {"item 0", "ítem l", "ítem 2",
                    "item 3", "ítem 4", "ítem 5"};

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

    class DListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            tv.setText("Ha pulsado el item " + which);
        }
    } // end Dialoglnterface.OnClickListener

    @Override
    protected Dialog onCreateDialog(int id) {
        DListener listener = new DListener();
        Dialog dialogo = null;
        if (id == 0) {
            Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("Esto es un diálogo con items");
            builder.setItems(items, listener);
            dialogo = builder.create();
        }
        return dialogo;
    }
}