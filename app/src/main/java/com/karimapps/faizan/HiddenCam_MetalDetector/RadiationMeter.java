package com.karimapps.faizan.HiddenCam_MetalDetector;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.github.anastr.speedviewlib.SpeedView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class RadiationMeter extends AppCompatActivity implements SensorEventListener {

    //https://github.com/anastr/SpeedView/wiki/1.-SpeedView

    private TextView tvStatus;
    private SensorManager sensorManager;
    public static DecimalFormat decimalFormat;
    private SpeedView speedoMeter;
    private double magnitude;
    private Float f, newFloat;
    ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
    PackageManager packageManager;

    private Context context;
    private AdView adView;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiation_meter);
        tvStatus = findViewById(R.id.tvStatus);
        context = RadiationMeter.this;

        getSupportActionBar().setTitle(R.string.radiation_meter);

        showAds();

        //define decimal formatter
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        symbols.setDecimalSeparator('.');
        decimalFormat = new DecimalFormat("#.000", symbols);

        speedoMeter = findViewById(R.id.speedView);
        speedoMeter.setUnit("");


    /*    packageManager = this.getPackageManager();
        boolean isAvailable = packageManager.hasSystemFeature(PackageManager.)*/
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Sensor mSensorMagneticField = sensorManager
//                .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
//        if(mSensorMagneticField!=null){
//            sensorManager.registerListener(this,
//                    sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
//                    SensorManager.SENSOR_DELAY_NORMAL
//            );
//        }else {
//            Toast.makeText(this, "Sensor not found", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            //get values for each axes
            float magX = event.values[0];
            float magY = event.values[1];
            float magZ = event.values[2];

            magnitude = Math.sqrt((magX * magX) + (magY * magY) + (magZ * magZ));

            f = Float.valueOf(String.valueOf(magnitude));

            newFloat = f - 20;

            speedoMeter.speedTo(newFloat, 4000);

            if (f <= 50){
                tvStatus.setText("TV/Mobile/Computer detected");
            }

            if (f >= 70 ){
                tvStatus.setText("Something suspicious detected");
            }

            if (f >= 90){
                tvStatus.setText("Trouble");
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
            }
            if (f == 0){
                Toast.makeText(this, "Not working", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RadiationMeter.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showAds() {
        MobileAds.initialize(context, getString(R.string.app_unit_id));
//        MobileAds.initialize(this,
//                "ca-app-pub-3940256099942544~3347511713");
        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        adView = findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        /*interstial ads*/
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

}
