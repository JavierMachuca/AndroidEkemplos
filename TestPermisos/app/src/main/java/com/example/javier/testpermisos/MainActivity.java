package com.example.javier.testpermisos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

    public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final int ROTATION_WAIT_TIME_MS = 100;
    private static final float ROTATION_THRESHOLD = 2.0f;

        /**
     * Sensors
     */
    private Sensor mSensorGyr;
    private long mShakeTime = 0;
    private long mRotationTime = 0;


    TextView texto;
    TextView texto2;


    //SHAREDPREFERENCES
    Button btnGuardar, btnMostrar;
    EditText textoSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnVibrar = (Button) findViewById(R.id.button1);
        Button btnGyro = (Button) findViewById(R.id.button2);

        //SHAREDPREFERENCES
         final Context context = this;
         SharedPreferences sharedpref =getSharedPreferences("ArchivoShared",context.MODE_PRIVATE);
         btnMostrar = (Button) findViewById(R.id.btnMostrar);
         btnGuardar = (Button) findViewById(R.id.btnGuardar);
         textoSP = (EditText) findViewById(R.id.textoSP);
        //

        texto = (TextView) findViewById(R.id.textView);
        texto2 = (TextView) findViewById(R.id.textView2);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpref = getPreferences(context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedpref.edit();
                edit.putString("valorGuardar",textoSP.getText().toString());
                edit.commit();
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedpref = getPreferences(context.MODE_PRIVATE);
                String textoSave = sharedpref.getString("valorGuardar","No hay nada guardado");

                Toast.makeText(context,"Dato en registro "+ textoSave,Toast.LENGTH_LONG).show();
            }
        });

        btnVibrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrarPhone();
            }
        });

        btnGyro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean b = accesWifi();
                if (b){
                    texto.setText("SI HAY INTERNET DISPONIBLE");
                }else{
                    texto.setText("NO HAY INTERNET DISPONIBLE");
                }
            }
        });
    }

        private void vibrarPhone() {
            Vibrator vibrate = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vibrate.vibrate(2000);
        }

        private boolean accesWifi() {

            ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivity != null) {
                NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                if (info != null) {
                    if (info.isConnected()) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {

                 if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                     texto.setText("asd");
                     texto2.setText("asd");
                   // mGyroz.setText(R.string.act_main_no_acuracy);
                }
                return;
            }

            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                texto.setText(" x = " + Float.toString(event.values[0]));
                texto2.setText(" y = " + Float.toString(event.values[1]));
                //mAccz.setText("z = " + Float.toString(event.values[2]));
                //detectShake(event);
            }
        }

        private void detectarRotacion(SensorEvent event){
            SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

            mSensorGyr = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

            long now = System.currentTimeMillis();

            if ((now - mRotationTime) > ROTATION_WAIT_TIME_MS) {
                mRotationTime = now;

                // Change background color if rate of rotation around any
                // axis and in any direction exceeds threshold;
                // otherwise, reset the color
                if (Math.abs(event.values[0]) > ROTATION_THRESHOLD ||
                        Math.abs(event.values[1]) > ROTATION_THRESHOLD ||
                        Math.abs(event.values[2]) > ROTATION_THRESHOLD) {

                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }
