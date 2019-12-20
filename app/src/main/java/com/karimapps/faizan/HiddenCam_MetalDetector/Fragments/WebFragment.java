package com.karimapps.faizan.HiddenCam_MetalDetector.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.karimapps.faizan.HiddenCam_MetalDetector.MainActivity;
import com.karimapps.faizan.HiddenCam_MetalDetector.R;


public class WebFragment extends Fragment {
    private View fragment_view;
    private Context context;
    private WebView myweb;
    private String url;
    private AdView adView;
    private InterstitialAd mInterstitialAd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_view = inflater.inflate(R.layout.web_fragment, container, false);
        context = getActivity();
        showAds();
        Bundle args = this.getArguments();
        if (args != null) {
            url = args.getString("url");
        }
        myweb = (WebView) fragment_view.findViewById(R.id.myweb);
//        myweb.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(request.getUrl().toString());
//                return false;
//            }
//        });
        final ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading please wait");
        progressDialog.show();
        if (TextUtils.isEmpty(url)) {
            myweb.loadUrl("http://www.karimapps.com");
        } else {
            myweb.loadUrl(url);
        }

        myweb.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);

                return false;

            }
        });
        progressDialog.dismiss();

        return fragment_view;
    }

    @Override
    public void onResume() {
        super.onResume();
        goBackMethod();
    }

    private void goBackMethod() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    startActivity(new Intent(context, MainActivity.class));

                    return true;

                }

                return false;
            }
        });
    }
    private void showAds() {
        MobileAds.initialize(context, getString(R.string.app_unit_id));
//        MobileAds.initialize(this,
//                "ca-app-pub-3940256099942544~3347511713");
        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        adView = fragment_view.findViewById(R.id.ad_view);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        /*interstial ads*/
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


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