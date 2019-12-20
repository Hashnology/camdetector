package com.karimapps.faizan.HiddenCam_MetalDetector;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.anastr.speedviewlib.SpeedView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Random;

public class MotionListner extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private SpeedView speedoMeter;
    private TextView tvStatus;
    ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
    private Context context;
    private AdView adView;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiation_meter);
        tvStatus = findViewById(R.id.tvStatus);

        context = MotionListner.this;

        getSupportActionBar().setTitle(R.string.radiation_meter);

        showAds();

        speedoMeter = findViewById(R.id.speedView);
        speedoMeter.setUnit("");

        //get sensor service
        sensorManager=(SensorManager)this.getSystemService(Context.SENSOR_SERVICE);
        //Tell which sensor you are going to use
        //And declare delay of sensor
        //Register all to your sensor object to use
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            //get x, y, z values
            float value[]=event.values;
            float x=value[0];
            float y=value[1];
            float z=value[2];
            //use the following formula
            //use gravity according to your place if you are on moon than use moon gravity
            float asr=(x*x+y*y+z*z)/(SensorManager.GRAVITY_EARTH*
                    SensorManager.GRAVITY_EARTH);

            Random r=new Random();
            int i=r.nextInt(10);

            asr = asr+10+i;

            speedoMeter.speedTo(asr);

            if (asr <= 10){
                tvStatus.setText("TV/Mobile/Computer detected");
            }

            if (asr >= 18){
                tvStatus.setText("Something suspicious detected");
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
            }

            if (asr >= 50){
                tvStatus.setText("Trouble");
                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MotionListner.this, MainActivity.class);
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
