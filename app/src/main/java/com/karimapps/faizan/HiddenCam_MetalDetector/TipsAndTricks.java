package com.karimapps.faizan.HiddenCam_MetalDetector;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.karimapps.faizan.HiddenCam_MetalDetector.Fragments.Bathroom;
import com.karimapps.faizan.HiddenCam_MetalDetector.Fragments.Bedroom;
import com.karimapps.faizan.HiddenCam_MetalDetector.Fragments.ChangingRoom;
import com.karimapps.faizan.HiddenCam_MetalDetector.Fragments.Outside;
import com.karimapps.faizan.HiddenCam_MetalDetector.Fragments.SuspiciousPlaces;

public class TipsAndTricks extends AppCompatActivity {

    private Context context;
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips_and_tricks);
        getSupportActionBar().setTitle(R.string.tips_tricks);

        context = TipsAndTricks.this;

        showAds();
    }

    public void bedroom(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new Bedroom();
        fragmentTransaction.replace(R.id.containerFragment, fragment);
        fragmentTransaction.commit();
    }

    public void outSide(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new Outside();
        fragmentTransaction.replace(R.id.containerFragment, fragment);
        fragmentTransaction.commit();
    }

    public void changingRoom(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new ChangingRoom();
        fragmentTransaction.replace(R.id.containerFragment, fragment);
        fragmentTransaction.commit();
    }

    public void bathroom(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new Bathroom();
        fragmentTransaction.replace(R.id.containerFragment, fragment);
        fragmentTransaction.commit();
    }

    public void commonPlaces(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Fragment fragment = new SuspiciousPlaces();
        fragmentTransaction.replace(R.id.containerFragment, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TipsAndTricks.this, MainActivity.class);
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
