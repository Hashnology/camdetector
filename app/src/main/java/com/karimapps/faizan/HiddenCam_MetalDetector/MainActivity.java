package com.karimapps.faizan.HiddenCam_MetalDetector;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.karimapps.faizan.HiddenCam_MetalDetector.Fragments.WebFragment;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private TextView tvRadiationMeter, tvTipsTricks, tvInfraredCamera;
    boolean doubleBackToExitPressedOnce = false;
    private InterstitialAd mInterstitialAd;
    private AdView adView;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        linkviews();
        showAds();

       /* getSupportActionBar().setTitle("Spy Camera");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.black_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);*/
    }

    private void linkviews() {
        tvRadiationMeter = findViewById(R.id.tvRadiationMeter);
        tvTipsTricks = findViewById(R.id.tvTipsTricks);
        tvInfraredCamera = findViewById(R.id.tvInfraredCamera);
    }

    public void radiationMeter(View view) {
        tvRadiationMeter.setBackgroundResource(R.drawable.green_button_onclick);
        tvInfraredCamera.setBackgroundResource(R.drawable.green_button);
        tvTipsTricks.setBackgroundResource(R.drawable.green_button);
        final MediaPlayer audio = MediaPlayer.create(this, R.raw.button_16);
        audio.start();

        showAds();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor mSensorMagneticField = sensorManager
                .getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if(mSensorMagneticField!=null){

            Intent intent = new Intent(MainActivity.this, RadiationMeter.class);
            startActivity(intent);
            finish();

        }else {
            Intent intent = new Intent(MainActivity.this, MotionListner.class);
            startActivity(intent);
            finish();
        }
    }

    public void infraredCamera(View view) {
        tvRadiationMeter.setBackgroundResource(R.drawable.green_button);
        tvInfraredCamera.setBackgroundResource(R.drawable.green_button_onclick);
        tvTipsTricks.setBackgroundResource(R.drawable.green_button);
        final MediaPlayer audio = MediaPlayer.create(this, R.raw.button_16);
        audio.start();

        showAds();

        Intent intent = new Intent(MainActivity.this, CameraActivity.class);
        startActivity(intent);
        finish();
    }

    public void tipsAndTricks(View view) {

        tvRadiationMeter.setBackgroundResource(R.drawable.green_button);
        tvInfraredCamera.setBackgroundResource(R.drawable.green_button);
        tvTipsTricks.setBackgroundResource(R.drawable.green_button_onclick);
        final MediaPlayer audio = MediaPlayer.create(this, R.raw.button_16);
        audio.start();

        showAds();

        Intent intent = new Intent(MainActivity.this, TipsAndTricks.class);
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


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
        showAlert();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 5000);
    }

    private void showAlert() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setTitle("Rate Us");
        builder1.setMessage("If you have liked this Detector app please rate it five star, also your positive comments are very useful appreciation for us to develop more apps like this one \n\nThanks");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Rate it",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        rateApp();

                    }
                });

        builder1.setNegativeButton(
                "Not Now",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        builder1.setNeutralButton("Exit App",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.rate_app:
//                Toast.makeText(context, "Rate App", Toast.LENGTH_SHORT).show();
                rateApp();
                return true;
            case R.id.about_us:
//                Toast.makeText(context, "About Us", Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = new WebFragment();
                fragmentTransaction.replace(R.id.container_fragment, fragment);
                fragmentTransaction.commit();
                return true;
            case R.id.how_to_use:
//                Toast.makeText(context, "Will be uploaded soon", Toast.LENGTH_SHORT).show();
                showDemo();
                return true;
            case R.id.more_apps:
//                Toast.makeText(context, "More Apps", Toast.LENGTH_SHORT).show();
                moreApps();
                return true;
            case R.id.gift_ads:
//                Toast.makeText(context, "Help", Toast.LENGTH_SHORT).show();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        if (mInterstitialAd.isLoaded())
                            mInterstitialAd.show();
                    }
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
//                mInterstitialAd.loadAd(request.build());
                    }
                });
                return true;
            case R.id.demo:
//                Toast.makeText(context, "Demo Video", Toast.LENGTH_SHORT).show();
                showDemo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void showDemo() {
        try {
            String videoId = "FnrCE5-gzIQ";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
            intent.putExtra("VIDEO_ID", videoId);
            startActivity(intent);
        } catch (Exception e) {

        }
    }

//    private void shareMoreAppslink() {
//        try {
//            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
//            try {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
//            } catch (android.content.ActivityNotFoundException anfe) {
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
//            }
//        } catch (Exception e) {
//            //e.toString();
//        }
//    }
//    private void sharelink() {
//
//
////        Intent intent = new Intent(Intent.ACTION_VIEW);
////        intent.setData(Uri.parse(
////                "https://play.google.com/store/apps/details?id=com.karimapps.faiza.ramdhankarimpro"));
////        intent.setPackage("com.android.vending");
////        startActivity(intent);
//
//
//        try {
//            Intent i = new Intent(Intent.ACTION_SEND);
//            i.setType("text/plain");
//            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
//            String sAux = "\nLet me recommend you this application\n\n";
//            sAux = sAux + "https://play.google.com/store/apps/details?id=com.karimapps.faizan.spy_camdetector \n\n";
//            i.putExtra(Intent.EXTRA_TEXT, sAux);
//            startActivity(Intent.createChooser(i, "choose one"));
//        } catch (Exception e) {
//            //e.toString();
//        }
//    }
//
//    private void shareMoreLink() {
//        try {
//            Intent i = new Intent(Intent.ACTION_SEND);
//            i.setType("text/plain");
//            i.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
//            String sAux = "\nLet me recommend you this application\n\n";
//            sAux = sAux + "https://play.google.com/store/apps/details?id=com.karimapps.idkorebiometric \n\n";
//            i.putExtra(Intent.EXTRA_TEXT, sAux);
//            startActivity(Intent.createChooser(i, "choose one"));
//        } catch (Exception e) {
//            //e.toString();
//        }
//    }
    private void rateApp() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(
                "https://play.google.com/store/apps/details?id=com.karimapps.faizan.spy_camdetector"));
        intent.setPackage("com.android.vending");
        startActivity(intent);
    }

    private void moreApps() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(
                "https://play.google.com/store/apps/details?id=com.karimapps.qrcodescanner"));
        intent.setPackage("com.android.vending");
        startActivity(intent);
    }
}
