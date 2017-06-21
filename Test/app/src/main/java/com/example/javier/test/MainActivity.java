package com.example.javier.test;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnToast;
    Button btnToastPer;
    Button btnNotif;
    Button btnDialogSelect;
    Button btnDialogAlert;
    Button btnDialogConf;
    Button btnSnack;

    TextView txtMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = (Button) findViewById(R.id.button);
        btnToastPer = (Button) findViewById(R.id.button2);
        btnNotif = (Button) findViewById(R.id.button3);
        btnDialogSelect = (Button) findViewById(R.id.button4);
        btnDialogAlert  = (Button) findViewById(R.id.button5);
        btnDialogConf   = (Button) findViewById(R.id.button6);
        btnSnack  = (Button) findViewById(R.id.button7);


        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(getApplicationContext(),"Toast por defecto", Toast.LENGTH_SHORT);
                toast1.setGravity(Gravity.BOTTOM,0,0);
                toast1.setDuration(Toast.LENGTH_LONG);
                toast1.show();
            }
        });

        btnToastPer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = new Toast(getApplicationContext());

                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.lytLayout));

                txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
                txtMsg.setText("Toast Personalizado");

                toast3.setDuration(Toast.LENGTH_SHORT);
                toast3.setView(layout);
                toast3.show();
            }
        });

        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder notificacion = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                        .setSmallIcon(android.R.drawable.stat_sys_warning)
                        .setLargeIcon(((BitmapDrawable)getResources()
                            .getDrawable(R.drawable.cara)).getBitmap())
                            .setContentTitle("Demo Notificacion")
                            .setContentText("Pulsa aca para redirect")
                            .setTicker("test app")
                            .setContentInfo("2");

                Intent notIntent =  new Intent(MainActivity.this, MainActivity.class);
                PendingIntent intentPendiente = PendingIntent.getActivity(MainActivity.this, 0, notIntent, 0);
                notificacion.setContentIntent(intentPendiente);

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                mNotificationManager.notify(1, notificacion.build());
            }
        });

        btnDialogSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoSeleccion dialogo = new DialogoSeleccion();
                dialogo.show(fragmentManager, "tagSeleccion");
            }
        });

        btnDialogAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoAlerta dialogo = new DialogoAlerta();
                dialogo.show(fragmentManager, "tagAlerta");
            }
        });

        btnDialogConf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                DialogoConfirmacion dialogo = new DialogoConfirmacion();
                dialogo.show(fragmentManager, "tagConfirmacion");
            }
        });

        btnSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Esto es una prueba de SnackBar", Snackbar.LENGTH_LONG)
                        .setAction("Btn en SnackBar", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();

            }
        });
    }


}
