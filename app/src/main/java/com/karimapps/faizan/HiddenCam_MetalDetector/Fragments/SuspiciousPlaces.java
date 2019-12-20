package com.karimapps.faizan.HiddenCam_MetalDetector.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.karimapps.faizan.HiddenCam_MetalDetector.R;
import com.karimapps.faizan.HiddenCam_MetalDetector.TipsAndTricks;

public class SuspiciousPlaces extends Fragment implements View.OnClickListener{

    private View fragmentView;
    private TextView textView;

    private Context context;
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.suspicious_places, container, false);
        context = getActivity();
        textView = fragmentView.findViewById(R.id.textView);
        textView.setOnClickListener(this);
        showAds();
        return fragmentView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textView:

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

                Intent intent = new Intent(context, TipsAndTricks.class);
                startActivity(intent);
        }
    }

    private void showAds() {
        MobileAds.initialize(context, getString(R.string.app_unit_id));
//        MobileAds.initialize(this,
//                "ca-app-pub-3940256099942544~3347511713");
        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        adView = fragmentView.findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        /*interstial ads*/
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onResume() {
        super.onResume();
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

    }
}
